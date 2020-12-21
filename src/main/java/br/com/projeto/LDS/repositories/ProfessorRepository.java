package br.com.projeto.LDS.repositories;

import br.com.projeto.LDS.domains.entities.Professor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends BaseRepository<Professor, Long> {
}
