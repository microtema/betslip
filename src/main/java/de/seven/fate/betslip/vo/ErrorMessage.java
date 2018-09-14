package de.seven.fate.betslip.vo;

import lombok.Data;

@Data
public class ErrorMessage {

    private String objectName;
    private String propertyName;
    private String defaultMessage;
}
