package br.com.projeto.LDS.domains.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "STUDANT")
@ToString
@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name="PK_STUDANT_PERSON", referencedColumnName="ID")
public class Studant  extends Person{

    private String code;

    @ManyToOne
    @JoinColumn(name = "PK_STUDANT_TCC")
    private TCC tcc;

}
