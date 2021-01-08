package br.com.projeto.LDS.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PerfilEnum {
    ADMIN(1,"ROLE_ADMIN"),
    PROFESSOR(2,"ROLE_PROFESSOR"),
    STUDANT(3,"ROLE_STUDANT");

    private final int code;
    private final String description;

    public static PerfilEnum toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (PerfilEnum personType : PerfilEnum.values()) {
            if (cod.equals(personType.getCode())) {
                return personType;
            }
        }

        throw new IllegalArgumentException();

    }
}
