package br.com.projeto.LDS.converters;

import br.com.projeto.LDS.enums.PerfilEnum;
import br.com.projeto.LDS.enums.PersonTypeEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PerfilEnumConverter implements AttributeConverter<PerfilEnum, Integer> {


    @Override
    public Integer convertToDatabaseColumn(PerfilEnum personTypeEnum) {
        return personTypeEnum.getCode();
    }


    @Override
    public PerfilEnum convertToEntityAttribute(Integer code) {
        return PerfilEnum.toEnum(code);
    }
}
