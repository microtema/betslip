package de.seven.fate.betslip.converter;

import de.seven.fate.betslip.entity.BetslipEntity;
import de.seven.fate.betslip.vo.Betslip;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import static de.seven.fate.betslip.constants.Constants.MAY_NOT_BE_NULL;

@Component
public class Betslip2BetslipEntityConverter implements DefaultConverter<Betslip, BetslipEntity> {

    @Override
    public void update(Betslip source, BetslipEntity target) {
        Validate.notNull(source, MAY_NOT_BE_NULL, "source");
        Validate.notNull(target, MAY_NOT_BE_NULL, "target");

        target.setId(source.getId());
        target.setUserId(source.getUserId());
        target.setTeamName(source.getTeamName());
        target.setAmount(source.getAmount());
        target.setPayoutFactor(source.getPayoutFactor());
    }

    @Override
    public BetslipEntity createInstance() {
        return new BetslipEntity();
    }
}
