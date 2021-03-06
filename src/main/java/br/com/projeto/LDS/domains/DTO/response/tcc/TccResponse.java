package br.com.projeto.LDS.domains.DTO.response.tcc;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TccResponse {

    private Long id;

    private String name;

    private String subject;

    @JsonManagedReference
    private List<PersonTccResponse> studants;

    @JsonManagedReference
    private List<FilesResponse> files;
}
