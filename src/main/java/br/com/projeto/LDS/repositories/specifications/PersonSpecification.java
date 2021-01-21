package br.com.projeto.LDS.repositories.specifications;

import br.com.projeto.LDS.domains.entities.Person;
import br.com.projeto.LDS.enums.PersonTypeEnum;
import org.springframework.data.jpa.domain.Specification;

public class PersonSpecification {


    public static  Specification<Person> personType(PersonTypeEnum personTypeEnum){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("personType"), personTypeEnum);
    }

}
