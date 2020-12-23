package br.com.projeto.LDS.config;

import br.com.projeto.LDS.domains.entities.Person;
import br.com.projeto.LDS.domains.entities.Professor;
import br.com.projeto.LDS.domains.entities.Studant;
import br.com.projeto.LDS.domains.entities.TCC;
import br.com.projeto.LDS.enums.PersonTypeEnum;
import br.com.projeto.LDS.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Configuration
@Profile("test")
@RequiredArgsConstructor
public class TestConfig {

    private final PersonRepository personRepository;

    @Bean
    public boolean instatiationDB(){
        Professor professor = Professor.builder()
                .tcc(new HashSet<TCC>(Set.of(new TCC())))
                .cpf("")
                .name("")
                .lastName("")
                .personType(PersonTypeEnum.PROFESSOR)
                .createdDate(LocalDate.now())
                .modifiedDate(LocalDate.now())
                .build();

        Studant studant = Studant.builder()
                .tcc(new TCC())
                .cpf("")
                .name("")
                .lastName("")
                .personType(PersonTypeEnum.PROFESSOR)
                .createdDate(LocalDate.now())
                .modifiedDate(LocalDate.now())
                .build();

        personRepository.save(professor);
        personRepository.save(studant);
        return true;

    }
}
