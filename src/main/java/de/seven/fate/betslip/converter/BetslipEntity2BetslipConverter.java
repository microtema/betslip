package de.seven.fate.betslip.converter;

import de.seven.fate.betslip.entity.BetslipEntity;
import de.seven.fate.betslip.vo.Betslip;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static de.seven.fate.betslip.constants.Constants.MAY_NOT_BE_NULL;

@Component
public class BetslipEntity2BetslipConverter implements DefaultConverter<BetslipEntity, Betslip> {

    private final LocalDateTimeConverter localDateConverter;

    public BetslipEntity2BetslipConverter(LocalDateTimeConverter localDateTimeConverter) {
        this.localDateConverter = localDateTimeConverter;
    }

    @Override
    public void update(BetslipEntity source, Betslip target) {
        Validate.notNull(source, MAY_NOT_BE_NULL, "source");
        Validate.notNull(target, MAY_NOT_BE_NULL, "target");

        target.setId(source.getId());
        target.setUserId(source.getUserId());
        target.setAmount(source.getAmount());
        target.setPayoutFactor(source.getPayoutFactor());
        target.setTeamName(source.getTeamName());

        target.setCreatedDate(localDateConverter.convertToDatabaseColumn(source.getCreatedDate()));
        target.setLastModifiedDate(localDateConverter.convertToDatabaseColumn(source.getLastModifiedDate()));
    }

    @Override
    public Betslip createInstance() {
        return new Betslip();
    }
}
