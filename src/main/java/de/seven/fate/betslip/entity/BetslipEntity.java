package de.seven.fate.betslip.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"userId","teamName", "amount", "payoutFactor"}, callSuper = false)
@Table(name = "BETSLIP", uniqueConstraints = {@UniqueConstraint(columnNames = {"userId","teamName"})})
public class BetslipEntity extends BaseAuditEntity<Long> {

    @NotNull
    private String userId;

    @NotNull
    @OrderBy("teamName ASC")
    private String teamName;

    @NotNull
    private Double amount;

    @NotNull
    private Double payoutFactor;
}
