package br.com.projeto.LDS.domains.DTO.response.person;

import br.com.projeto.LDS.domains.entities.TCC;
import br.com.projeto.LDS.enums.PersonTypeEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
@SuperBuilder(toBuilder = true)
public class StudantResponse extends PersonResponse {

    public StudantResponse() {
        super(PersonTypeEnum.STUDANT);
    }


    private PersonTccResponse tcc;

    private String code;
}
