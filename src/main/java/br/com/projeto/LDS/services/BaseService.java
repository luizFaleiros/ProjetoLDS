package br.com.projeto.LDS.services;

import br.com.projeto.LDS.domains.entities.BaseEntity;

import java.util.List;
import java.util.Map;

public interface BaseService<ENTITY extends BaseEntity, DTO> {

    List<ENTITY> listAll();

    ENTITY getById(Long id);

    void saveAll(List<DTO> personList,String token);

    void save(DTO person,String token);

    ENTITY update(DTO person, Long id,String token);

    ENTITY patch(Map<String, Object> patch, Long id,String token);

}
