package br.com.projeto.LDS.controller;

import br.com.projeto.LDS.domains.DTO.TCCDTO;
import br.com.projeto.LDS.domains.DTO.filter.params.TccFilter;
import br.com.projeto.LDS.domains.DTO.response.tcc.TccResponse;
import br.com.projeto.LDS.domains.mappers.TccMapper;
import br.com.projeto.LDS.services.TccService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tcc")
@RequiredArgsConstructor
public class TCCController {

    private final TccService tccService;
    private final TccMapper tccMapper;

    @PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
    @GetMapping("/list")
    ResponseEntity<List<TccResponse>> getProfessorTccs(TccFilter filter) {
        return ResponseEntity.ok(tccService.getAllByProfessor(filter).stream().map(tccMapper::toResponse).collect(Collectors.toList()));
    }

    @PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
    @PostMapping("/save")
    ResponseEntity<Void> saveProfessorTccs(@Valid @RequestBody TCCDTO tcc) {
        tccService.save(tcc);
        return ResponseEntity.created(URI.create("Deu_certo")).body(null);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','STUDANT')")
    @PutMapping("/upload-file/{tccId}")
    ResponseEntity<URI> uploadFile(@PathVariable Long tccId, @RequestParam(name="file") MultipartFile file) {
        URI url = tccService.uploadTccFile(file,tccId);
        return ResponseEntity.created(url).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','STUDANT')")
    @GetMapping("/detail/{tccId}")
    ResponseEntity<TccResponse> tccDetais(@PathVariable Long tccId) {
        return ResponseEntity.ok(tccMapper.toResponse(tccService.findById(tccId)));
    }


}
