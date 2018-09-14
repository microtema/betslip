package de.seven.fate.betslip.converter;

import de.seven.fate.betslip.entity.BetslipEntity;
import de.seven.fate.model.builder.annotation.Model;
import de.seven.fate.model.builder.util.FieldInjectionUtil;
import de.seven.fate.betslip.vo.Betslip;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BetslipEntity2BetslipConverterTest {

    @Autowired
    BetslipEntity2BetslipConverter sut;

    @Model
    BetslipEntity entity;

    @Before
    public void setUp() {
        FieldInjectionUtil.injectFields(this);
    }

    @Test
    public void convert() {

        Betslip model = sut.convert(entity);

        assertNotNull(model);

        assertEquals(entity.getTeamName(), model.getTeamName());
        assertEquals(entity.getAmount(), model.getAmount());
        assertEquals(entity.getPayoutFactor(), model.getPayoutFactor());
        assertEquals(entity.getUserId(), model.getUserId());
    }

    @Test
    public void createInstance() {

        assertNotNull(sut.createInstance());
    }
}