package br.com.projeto.LDS.controller;

import br.com.projeto.LDS.domains.entities.Person;
import br.com.projeto.LDS.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/getPessoa/{id}")
    public ResponseEntity<Person> listar(@PathVariable Long id){

        return ResponseEntity.ok(personService.getById(id));
    }
}
