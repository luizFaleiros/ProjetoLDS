package br.com.projeto.LDS.repositories;

import br.com.projeto.LDS.domains.entities.Professor;
import br.com.projeto.LDS.domains.entities.Studant;
import br.com.projeto.LDS.domains.entities.TCC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TccRepository extends JpaRepository<TCC, Long>, JpaSpecificationExecutor<TCC> {

    @Transactional(readOnly = true)
    Page<TCC> findByProfessor(Professor id, Pageable page);

    Optional<TCC> findByStudantsIn(Collection<Studant> asList);


}
