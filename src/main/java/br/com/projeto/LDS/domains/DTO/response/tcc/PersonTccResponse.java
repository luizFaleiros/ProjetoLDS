package br.com.projeto.LDS.domains.DTO.response.tcc;

import br.com.projeto.LDS.enums.PersonTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonTccResponse {

    private Long id;

    private String lastName;

    private String firstName;

    private PersonTypeEnum personType;

    private String email;
}
