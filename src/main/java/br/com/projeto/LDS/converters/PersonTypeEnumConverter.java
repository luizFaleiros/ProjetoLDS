package br.com.projeto.LDS.converters;

import br.com.projeto.LDS.enums.PersonTypeEnum;
import io.swagger.models.auth.In;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PersonTypeEnumConverter implements AttributeConverter<PersonTypeEnum, Integer> {


    @Override
    public Integer convertToDatabaseColumn(PersonTypeEnum personTypeEnum) {
        return personTypeEnum.getCode();
    }

    @Override
    public PersonTypeEnum convertToEntityAttribute(Integer code) {
        return PersonTypeEnum.toEnum(code);
    }
}
