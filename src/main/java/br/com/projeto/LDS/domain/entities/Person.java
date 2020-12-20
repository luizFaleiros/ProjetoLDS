package br.com.projeto.LDS.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Person implements Serializable {

    private Long id;

    private String nome;

    private String sobreNome;

    private String cpf;

}
