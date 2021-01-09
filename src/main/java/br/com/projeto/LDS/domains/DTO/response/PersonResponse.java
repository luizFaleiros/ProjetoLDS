package br.com.projeto.LDS.domains.DTO.response;

import br.com.projeto.LDS.domains.DTO.ProfessorDTO;
import br.com.projeto.LDS.domains.DTO.StudantDTO;
import br.com.projeto.LDS.enums.PersonTypeEnum;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "personType")
@JsonSubTypes({@JsonSubTypes.Type(value = ProfessorResponse.class, name = "PROFESSOR"), @JsonSubTypes.Type(value = StudantResponse.class, name = "STUDANT")})
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class PersonResponse {

    public PersonResponse(PersonTypeEnum personType) {
        this.personType = personType;
    }

    private String lastName;

    private String firstName;

    private PersonTypeEnum personType;

    private String email;

}
