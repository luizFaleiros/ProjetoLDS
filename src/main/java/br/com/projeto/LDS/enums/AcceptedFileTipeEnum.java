package br.com.projeto.LDS.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AcceptedFileTipeEnum {
    PDF(1,"PDF"),
    DOC(2,"DOC"),
    DOCX(3,"DOCX"),
    RAR(4,"RAR"),
    ZIP(5,"ZIP"),
    Z_SEVEN(6,"7Z"),
    TXT(7,"TXT");

    private final int code;
    private final String description;

    public static AcceptedFileTipeEnum toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (AcceptedFileTipeEnum acceptedFileTipeEnum : AcceptedFileTipeEnum.values()) {
            if (cod.equals(acceptedFileTipeEnum.getCode())) {
                return acceptedFileTipeEnum;
            }
        }

        throw new IllegalArgumentException("Tipo de arquivo não encontrado");

    }
    public static AcceptedFileTipeEnum toEnumByDescription(String description) {
        if (description == null) {
            return null;
        }
        for (AcceptedFileTipeEnum acceptedFileTipeEnum : AcceptedFileTipeEnum.values()) {
            if (description.equals(acceptedFileTipeEnum.getDescription())) {
                return acceptedFileTipeEnum;
            }
        }

        throw new IllegalArgumentException("Tipo de arquivo não encontrado");

    }

}
