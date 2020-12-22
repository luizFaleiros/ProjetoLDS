package br.com.projeto.LDS.domains.DTO;

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
public class StudantDTO extends PersonDTO {


    public StudantDTO() {
        super(PersonTypeEnum.STUDANT);
    }

    private TCC tcc;

    private String code;
}
