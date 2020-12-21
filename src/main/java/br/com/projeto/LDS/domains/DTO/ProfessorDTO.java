package br.com.projeto.LDS.domains.DTO;

import br.com.projeto.LDS.enums.PersonTypeEnum;

public class ProfessorDTO extends PersonDTO {

    public ProfessorDTO() {
        super(PersonTypeEnum.PROFESSOR);
    }
}
