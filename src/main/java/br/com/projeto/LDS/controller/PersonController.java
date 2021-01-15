package br.com.projeto.LDS.controller;

import br.com.projeto.LDS.domains.DTO.PersonDTO;
import br.com.projeto.LDS.domains.DTO.response.PersonResponse;
import br.com.projeto.LDS.domains.entities.Person;
import br.com.projeto.LDS.domains.mappers.PersonMapper;
import br.com.projeto.LDS.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    private final PersonMapper personMapper;

    @PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','STUDANT')")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<PersonResponse> getById(@PathVariable Long id) {

        return ResponseEntity.ok(personMapper.toResponse(personService.getById(id)));
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody PersonDTO person) {
        personService.save(person);
        return ResponseEntity.created(URI.create("Deu_certo")).body(null);
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<PersonResponse>> list() {
        return ResponseEntity.ok(personService.listAll()
                .stream()
                .map(personMapper::toResponse)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','STUDANT')")
    @PutMapping("/update/{id}")
    public ResponseEntity<PersonResponse> update(@Valid @RequestBody PersonDTO personDTO,
                                         @PathVariable Long id) {

        return ResponseEntity.ok(personMapper.toResponse(personService.update(personDTO,id)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','STUDANT')")
    @PatchMapping("/patch/{id}")
    public ResponseEntity<PersonResponse> update(@Valid @RequestBody Map<String, Object> personDTO,
                                         @PathVariable Long id) {

        return ResponseEntity.ok(personMapper.toResponse(personService.patch(personDTO,id)));
    }

}
