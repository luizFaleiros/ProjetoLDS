package br.com.projeto.LDS.repositories.specifications;

import br.com.projeto.LDS.domains.entities.Professor;
import br.com.projeto.LDS.domains.entities.TCC;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TccSpecification implements Specification<TCC> {


    private final PageRequest page;
    private final Professor professor;

    public TccSpecification(PageRequest page, Professor professor) {

        this.page = page;
        this.professor = professor;
    }

    @Override
    public Specification<TCC> and(Specification<TCC> other) {
        return null;
    }

    @Override
    public Specification<TCC> or(Specification<TCC> other) {
        return null;
    }

    @Override
    public Predicate toPredicate(Root<TCC> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();
        this.professorFilter(root, criteriaBuilder, predicate);
        return null;
    }

    private void professorFilter(Root<TCC> root, CriteriaBuilder criteriaBuilder, Predicate predicate) {
    }

}

