package br.com.projeto.LDS.repositories;

import br.com.projeto.LDS.domains.entities.AcceptedFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<AcceptedFile, Long> {
}
