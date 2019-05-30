package br.com.welson.meucontrole.seguranca;

import br.com.welson.meucontrole.seguranca.filtros.FiltroAutenticacao;
import br.com.welson.meucontrole.seguranca.filtros.FiltroAutorizacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

@EnableWebSecurity
public class Configuracao extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public Configuracao(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> getCorsConfiguration())
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/usuario/**").hasRole("USUARIO")
                .antMatchers("/administrador/**").hasRole("ADMINISTRADOR")
                .and()
                .addFilter(new FiltroAutenticacao(authenticationManager()))
                .addFilter(new FiltroAutorizacao(authenticationManager(), userDetailsService));
    }

    private CorsConfiguration getCorsConfiguration() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        return corsConfiguration;
    }
}
