package br.com.projeto.LDS.domains.DTO;

import br.com.projeto.LDS.enums.PersonTypeEnum;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "personType")
@JsonSubTypes({@JsonSubTypes.Type(value = ProfessorDTO.class, name = "PROFESSOR"), @JsonSubTypes.Type(value = StudantDTO.class, name = "STUDANT")})
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Validated
public abstract class PersonDTO {

    public PersonDTO(PersonTypeEnum personType) {
        this.personType = personType;
    }

    @Valid
    @NotBlank(message = "Ultimo nome não pode ser vazio")
    private String lastName;

    @Valid
    @NotBlank(message = "Primeairo nome não pode ser vazio")
    private String firstName;

    @Valid
    @NotBlank
    @Size(min = 11, max = 11,message = "Cpf não pode ser diferente de 11 caracteres")
    private String cpf;

    @Valid
    @NotNull(message = "Tipo de pessoa não pode vir vazio")
    private PersonTypeEnum personType;

    @Valid
    @NotBlank(message = "Senha não pode vir vazia")
    private String password;

    @Valid
    @NotBlank(message = "Email não pode ser vazio")
    @Email(message = "Essa string não é um email valido")
    private String email;

}
