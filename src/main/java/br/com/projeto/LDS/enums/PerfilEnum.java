package br.com.projeto.LDS.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PerfilEnum {
    ADMIN(1,"ROLE_ADMIN"),
    LIDER(2,"ROLE_MODERATOR"),
    PARTICIPANT(3,"ROLE_PARTICIPANT");

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
