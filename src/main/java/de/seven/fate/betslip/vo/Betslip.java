package de.seven.fate.betslip.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Value Object
 */
@Data
public class Betslip {

    // May be null
    private Long id;

    @NotNull
    private String userId;

    @NotNull
    private String teamName;

    @NotNull
    private Double amount;

    @NotNull
    private Double payoutFactor;

    private Date createdDate;

    private Date lastModifiedDate;
}
