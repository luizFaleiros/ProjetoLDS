package br.com.projeto.LDS.controller;

import br.com.projeto.LDS.domain.entities.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @GetMapping("/getPessoa")
    public List<Person> listar(){
        Person person = Person.builder().build();
        Person person2 = Person.builder().build();
        List<Person> personList = new ArrayList<>();
        personList.add(person2);
        personList.add(person);
        return personList;
    }
}
