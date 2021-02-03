package br.com.projeto.LDS.domains.DTO.response.tcc;

import br.com.projeto.LDS.enums.AcceptedFileTipeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilesResponse {

    private Long id;

    private String TccName;

    private AcceptedFileTipeEnum fileTipe;

    private URI url;

    private String whoModified;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate lastModified;
}
