package br.com.projeto.LDS.services;

import br.com.projeto.LDS.domains.entities.BaseEntity;

import java.util.List;

public interface BaseService<ENTITY extends BaseEntity, DTO> {

    List<ENTITY> listAll();

    ENTITY getById(Long id);

    void saveAll(List<DTO> personList);

    void save(DTO person);

}
