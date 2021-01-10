package br.com.projeto.LDS.domains.DTO.response.person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonTccResponse {

    private Long id;

    private String name;
}
