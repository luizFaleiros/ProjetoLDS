package br.com.projeto.LDS.domains.DTO.response.tcc;

import br.com.projeto.LDS.enums.AcceptedFileTipeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilesResponse {

    private String TccName;
    private AcceptedFileTipeEnum fileTipe;
    private URI url;
    private String whoModified;
    private LocalDate lastModified;
}
