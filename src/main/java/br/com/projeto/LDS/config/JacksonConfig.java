package br.com.projeto.LDS.config;

import br.com.projeto.LDS.domains.DTO.ProfessorDTO;
import br.com.projeto.LDS.domains.DTO.StudantDTO;
import br.com.projeto.LDS.domains.entities.Professor;
import br.com.projeto.LDS.domains.entities.Studant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(Professor.class);
                objectMapper.registerSubtypes(Studant.class);
                objectMapper.registerSubtypes(StudantDTO.class);
                objectMapper.registerSubtypes(ProfessorDTO.class);
                super.configure(objectMapper);
            }
        };
        return builder;
    }
}
