package br.com.projeto.LDS.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public enum PersonTypeEnum {
    PROFESSOR(1, "Professor"),
    ADMIN(1, "Admin"),
    STUDANT(2, "Estudante");

    private final int code;
    private final String description;


    public static PersonTypeEnum toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (PersonTypeEnum personType : PersonTypeEnum.values()) {
            if (cod.equals(personType.getCode())) {
                return personType;
            }
        }
        throw new IllegalArgumentException();

    }
}
