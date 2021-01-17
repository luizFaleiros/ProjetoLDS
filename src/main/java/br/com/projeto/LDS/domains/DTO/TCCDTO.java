package br.com.projeto.LDS.domains.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TCCDTO {
    @NotEmpty
    String name;

    @NotEmpty
    String subjects;
    List<Long> studants;
}
