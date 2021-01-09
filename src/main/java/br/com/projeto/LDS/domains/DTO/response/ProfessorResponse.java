package br.com.projeto.LDS.domains.DTO.response;

import br.com.projeto.LDS.domains.entities.TCC;
import br.com.projeto.LDS.enums.PersonTypeEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
@SuperBuilder(toBuilder = true)
public class ProfessorResponse extends PersonResponse{

    public ProfessorResponse() {
        super(PersonTypeEnum.PROFESSOR);
    }

    private List<TCC> tccs;
}
