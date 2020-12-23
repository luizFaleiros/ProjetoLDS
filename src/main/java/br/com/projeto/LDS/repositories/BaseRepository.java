package br.com.projeto.LDS.repositories;

import br.com.projeto.LDS.domains.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID extends Long> extends JpaRepository<T,ID> {
}
