package br.com.projeto.LDS.domains.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class LoginDTO {

    @Valid
    @NotBlank(message = "Email não pode ser vazio")
    private String email;

    @Valid
    @NotBlank(message = "senha não pode ser vazia")
    private String password;
}
