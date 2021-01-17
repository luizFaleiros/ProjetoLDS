package br.com.projeto.LDS.converters;

import br.com.projeto.LDS.enums.AcceptedFileTipeEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AcceptedFileTipeEnumConverter implements AttributeConverter<AcceptedFileTipeEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(AcceptedFileTipeEnum acceptedFileTipeEnum) {
        return acceptedFileTipeEnum.getCode();
    }

    @Override
    public AcceptedFileTipeEnum convertToEntityAttribute(Integer code) {
        return AcceptedFileTipeEnum.toEnum(code);
    }
}
