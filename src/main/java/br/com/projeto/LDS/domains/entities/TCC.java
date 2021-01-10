package br.com.projeto.LDS.domains.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "TCC")
@ToString
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TCC extends BaseEntity implements Serializable {

    @Column(name = "NAME")
    private String name;

    @Column(name = "SUBJECTS")
    private String subjects;

    @JsonManagedReference
    @OneToMany(fetch =FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "tcc", orphanRemoval = true)
    private List<Studant> studants;

    @JsonBackReference
    @ManyToOne(targetEntity = Professor.class, fetch = FetchType.LAZY)
    @JoinColumn(name="PROFESSOR_ID")
    private Professor professor;

}
