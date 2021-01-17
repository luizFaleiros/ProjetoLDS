package br.com.projeto.LDS.domains.DTO.filter.params;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TccFilter {
    private Integer page;
    private Integer linesPerPage;
    private String orderBy;
    private Sort.Direction direction;

}
