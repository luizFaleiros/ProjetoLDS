package br.com.projeto.LDS.domains.DTO;

import br.com.projeto.LDS.enums.PersonTypeEnum;

public class StudantDTO extends PersonDTO {

    public StudantDTO() {
        super(PersonTypeEnum.STUDANT);
    }
}
