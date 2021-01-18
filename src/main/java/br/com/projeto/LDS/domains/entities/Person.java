package br.com.projeto.LDS.domains.entities;

import br.com.projeto.LDS.enums.PerfilEnum;
import br.com.projeto.LDS.enums.PersonTypeEnum;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "PERSON")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class Person extends BaseEntity implements Serializable {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "CPF",length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(name="PERSON_TYPE", nullable = false)
    private PersonTypeEnum personType;

    @Column(name="EMAIL",unique = true, nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String pass;

    @Column(name = "ISDELETED", nullable = false)
    private Boolean isDeleted = false;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIL")
    private Set<PerfilEnum> perfis = new HashSet<>();

    public void addPerfil(PerfilEnum perfilEnum){
        if(Objects.isNull(this.perfis)){
            this.perfis = new HashSet<>();
        }
        this.perfis.add(perfilEnum);
    }

}
