package br.com.projeto.LDS.domains.DTO;

import br.com.projeto.LDS.domains.entities.TCC;
import br.com.projeto.LDS.enums.PersonTypeEnum;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
@SuperBuilder(toBuilder = true)
public class ProfessorDTO extends PersonDTO {

    public ProfessorDTO() {
        super(PersonTypeEnum.PROFESSOR);
    }

    private List<TCC> tccs;
}
