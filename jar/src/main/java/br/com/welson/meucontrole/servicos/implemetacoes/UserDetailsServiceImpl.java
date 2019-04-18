package br.com.welson.meucontrole.servicos.implemetacoes;

import br.com.welson.meucontrole.persistencia.modelos.Usuario;
import br.com.welson.meucontrole.persistencia.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepositorio usuarioRepositorio;

    @Autowired
    public UserDetailsServiceImpl(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        return new CustomUserDetails(carregarUsuarioPorNomeUsuario(usuario));
    }

    public Usuario carregarUsuarioPorNomeUsuario(String usuario) {
        return usuarioRepositorio.findByUsuario(usuario).orElseThrow(() -> new UsernameNotFoundException("Usuario não existe!"));
    }

    private static class CustomUserDetails extends Usuario implements UserDetails {

        private CustomUserDetails(Usuario usuario) {
            super(usuario);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> authorityListAdministrador = AuthorityUtils.createAuthorityList("ROLE_ADMINISTRADOR", "ROLE_USUARIO");
            List<GrantedAuthority> authorityListUsuario = AuthorityUtils.createAuthorityList("ROLE_USUARIO");
            return isAdministrador() ? authorityListAdministrador : authorityListUsuario;
        }

        @Override
        public String getPassword() {
            return getSenha();
        }

        @Override
        public String getUsername() {
            return getUsuario();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return isAtiva();
        }
    }
}
