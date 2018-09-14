package de.seven.fate.betslip.converter;

import de.seven.fate.model.builder.annotation.Model;
import de.seven.fate.model.builder.util.FieldInjectionUtil;
import de.seven.fate.betslip.entity.BetslipEntity;
import de.seven.fate.betslip.vo.Betslip;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Betslip2BetslipEntityConverterTest {

    @Autowired
    Betslip2BetslipEntityConverter sut;

    @Model
    Betslip model;

    @Before
    public void setUp() {
        FieldInjectionUtil.injectFields(this);
    }

    @Test
    public void convert() {

        BetslipEntity entity = sut.convert(model);

        assertNotNull(entity);

        assertEquals(model.getTeamName(), entity.getTeamName());
        assertEquals(model.getAmount(), entity.getAmount());
        assertEquals(model.getPayoutFactor(), entity.getPayoutFactor());
        assertEquals(model.getUserId(), entity.getUserId());
        assertEquals(model.getId(), entity.getId());
    }

    @Test
    public void createInstance() {

        assertNotNull(sut.createInstance());
    }
}