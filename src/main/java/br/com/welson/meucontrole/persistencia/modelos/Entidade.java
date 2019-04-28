package br.com.welson.meucontrole.persistencia.modelos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

import static javax.persistence.GenerationType.AUTO;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
public class Entidade implements Serializable {

    @Id
    @GeneratedValue(strategy = AUTO)
    protected Long id;

}
