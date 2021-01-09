package br.com.projeto.LDS.domains.DTO.response;

import br.com.projeto.LDS.domains.entities.TCC;
import br.com.projeto.LDS.enums.PersonTypeEnum;
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
public class StudantResponse extends PersonResponse{

    public StudantResponse() {
        super(PersonTypeEnum.STUDANT);
    }

    private TCC tcc;

    private String code;
}
