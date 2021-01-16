package br.com.projeto.LDS.domains.entities;

import br.com.projeto.LDS.enums.AcceptedFileTipeEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.net.URI;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "FILE")
@ToString
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AcceptedFile extends BaseEntity{

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_TYPE")
    private AcceptedFileTipeEnum fileType;

    @Column(name = "URI")
    private String url;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "TCC_ID")
    private TCC tcc;
}
