package br.com.projeto.LDS.config;

import br.com.projeto.LDS.domains.entities.Professor;
import br.com.projeto.LDS.domains.entities.Studant;
import br.com.projeto.LDS.domains.entities.TCC;
import br.com.projeto.LDS.enums.PerfilEnum;
import br.com.projeto.LDS.enums.PersonTypeEnum;
import br.com.projeto.LDS.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Configuration
@Profile("test")
@RequiredArgsConstructor
public class TestConfig {

    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Bean
    public boolean instatiationDB(){
        Professor professor = Professor.builder()
                .tcc(Collections.singletonList(new TCC()))
                .cpf("teste")
                .name("teste")
                .lastName("teste")
                .email("email@email.com")
                .perfis(Set.of(PerfilEnum.ADMIN))
                .pass(passwordEncoder.encode("teste"))
                .personType(PersonTypeEnum.PROFESSOR)
                .createdDate(LocalDate.now())
                .modifiedDate(LocalDate.now())
                .build();

        Studant studant = Studant.builder()
                .tcc(new TCC())
                .cpf("teste")
                .name("teste")
                .email("email2@email.com")
                .lastName("teste")
                .perfis(Set.of(PerfilEnum.STUDANT))
                .personType(PersonTypeEnum.PROFESSOR)
                .createdDate(LocalDate.now())
                .modifiedDate(LocalDate.now())
                .build();

        personRepository.save(professor);
        personRepository.save(studant);
        return true;

    }
}
