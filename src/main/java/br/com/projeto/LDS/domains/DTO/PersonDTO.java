package br.com.projeto.LDS.domains.DTO;

import br.com.projeto.LDS.enums.PersonTypeEnum;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "personType")
@JsonSubTypes({@JsonSubTypes.Type(value = ProfessorDTO.class, name = "PROFESSOR"), @JsonSubTypes.Type(value = StudantDTO.class, name = "STUDANT")})
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public abstract class PersonDTO {

    public PersonDTO(PersonTypeEnum personType) {
        this.personType = personType;
    }

    private String lastName;

    private String firstName;

    private String cpf;

    private PersonTypeEnum personType;

}
