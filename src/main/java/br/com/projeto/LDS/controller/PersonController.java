package br.com.projeto.LDS.controller;

import br.com.projeto.LDS.domains.DTO.PersonDTO;
import br.com.projeto.LDS.domains.entities.Person;
import br.com.projeto.LDS.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Person> getById(@PathVariable Long id) {

        return ResponseEntity.ok(personService.getById(id));
    }

    @PostMapping("/save-all")
    public ResponseEntity<Void> saveAll(@RequestBody List<PersonDTO> personList) {
        personService.saveAll(personList);
        return ResponseEntity.created(URI.create("Deu certo")).body(null);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody PersonDTO person) {
        personService.save(person);
        return ResponseEntity.created(URI.create("Deu_certo")).body(null);
    }


    @GetMapping("/list")
    public ResponseEntity<List<Person>> list() {
        return ResponseEntity.ok(personService.listAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Person> update(@Valid @RequestBody PersonDTO personDTO,
                                         @PathVariable Long id) {

        return ResponseEntity.ok(personService.update(personDTO,id));
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Person> update(@Valid @RequestBody Map<String, Object> personDTO,
                                         @PathVariable Long id) {

        return ResponseEntity.ok(personService.patch(personDTO,id));
    }
}
