package br.com.projeto.LDS.domains.DTO;

import br.com.projeto.LDS.enums.PersonTypeEnum;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "personType")
@JsonSubTypes({@JsonSubTypes.Type(value = ProfessorDTO.class, name = "PROFESSOR"), @JsonSubTypes.Type(value = StudantDTO.class, name = "STUDANT")})
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public abstract class PersonDTO {

    public PersonDTO(PersonTypeEnum personType) {
        this.personType = personType;
    }

    @NotBlank
    private String lastName;

    @NotBlank
    private String firstName;

    @NotBlank
    @Size(min = 11, max = 11,message = "Cpf não pode ser diferente de 11 caracteres")
    private String cpf;

    @NotBlank
    private PersonTypeEnum personType;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

}
