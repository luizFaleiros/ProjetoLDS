package br.com.projeto.LDS.controller;

import br.com.projeto.LDS.domains.DTO.PersonDTO;
import br.com.projeto.LDS.domains.entities.Person;
import br.com.projeto.LDS.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Person> getById(@PathVariable Long id){

        return ResponseEntity.ok(personService.getById(id));
    }

    @PostMapping("/save-all")
    public ResponseEntity<Void> saveAll(@RequestBody List<PersonDTO> personList){
        personService.saveAll(personList);
        return ResponseEntity.created(URI.create("Deu certo")).body(null);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody PersonDTO person){
        personService.save(person);
        return ResponseEntity.created(URI.create("Deu certo")).body(null);
    }


    @GetMapping("/list")
    public ResponseEntity<List<Person>> list(){

        return ResponseEntity.ok(personService.listAll());
    }
}
