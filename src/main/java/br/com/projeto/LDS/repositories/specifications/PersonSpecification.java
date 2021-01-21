package br.com.projeto.LDS.repositories.specifications;

import br.com.projeto.LDS.domains.entities.Person;
import br.com.projeto.LDS.enums.PersonTypeEnum;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PersonSpecification{


    public static  Specification<Person> personType(PersonTypeEnum personTypeEnum){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("personType"), personTypeEnum);
    }

    public static  Specification<Person> deleted(Boolean isDeleted){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isDeleted"), isDeleted);
    }



}
