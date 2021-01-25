package br.com.projeto.LDS.domains.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TCCDTO {
    @Valid
    @NotBlank(message = "Nome do TCC é obrigatorio")
    String name;

    @Valid
    @NotBlank(message = "subject do tcc é obrigatório")
    String subjects;

    List<Long> studants;
}
