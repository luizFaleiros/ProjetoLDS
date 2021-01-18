package br.com.projeto.LDS.services;

import br.com.projeto.LDS.domains.entities.BaseEntity;
import br.com.projeto.LDS.domains.entities.Studant;

import java.util.List;
import java.util.Map;

public interface BaseService<ENTITY extends BaseEntity, DTO> {

    List<ENTITY> listAll();

    ENTITY getById(Long id);

    void saveAll(List<ENTITY> dtos,String token);

    void save(ENTITY person);

    ENTITY update(ENTITY dto, Long id);

    ENTITY patch(Map<String, Object> patch, Long id);

    void logicalDelete(Long id);

}
