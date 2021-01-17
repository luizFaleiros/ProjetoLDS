package br.com.projeto.LDS.repositories;

import br.com.projeto.LDS.domains.entities.Person;
import br.com.projeto.LDS.domains.entities.Studant;
import br.com.projeto.LDS.enums.PersonTypeEnum;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends BaseRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    Optional<Person> findByEmail(String email);

    List<Studant> findByIdInAndPersonType(List<Long> id, PersonTypeEnum personTypeEnum);
}
