package br.com.projeto.LDS.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PersonTypeEnum {
    PROFESSOR(1, "Professor"),
    STUDANT(2, "Estudante");

    private final int code;
    private final String description;

    public String getDescription(){
        return this.description;
    }
    public int getCode(){
        return this.code;
    }

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
