package de.seven.fate.betslip.facade;

import de.seven.fate.model.builder.annotation.Model;
import de.seven.fate.model.builder.util.FieldInjectionUtil;
import de.seven.fate.betslip.service.BetslipService;
import de.seven.fate.betslip.vo.Betslip;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BetslipFacadeTest {

    @InjectMocks
    BetslipFacade sut;

    @Mock
    BetslipService betslipService;

    @Mock
    Betslip model;

    @Model
    Long id;

    @Before
    public void setUp() {
        FieldInjectionUtil.injectFields(this);
    }

    @Test
    public void getTextFiles() {

        sut.getBetslips();

        verify(betslipService).getBetslips();
    }

    @Test
    public void createTextFile() {

        sut.createBetslip(model);

        verify(betslipService).createBetslip(model);
    }

    @Test
    public void updateTextFile() {

        sut.getBetslip(model);

        verify(betslipService).updateBetslip(model);
    }

    @Test
    public void getTextFile() {

        sut.getBetslip(id);

        verify(betslipService).getBetslip(id);
    }

    @Test
    public void deleteTextFile() {

        sut.deleteBetslip(id);

        verify(betslipService).deleteBetslip(id);
    }
}