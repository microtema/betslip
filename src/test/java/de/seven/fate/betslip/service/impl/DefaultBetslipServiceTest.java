package de.seven.fate.betslip.service.impl;

import de.seven.fate.model.builder.annotation.Model;
import de.seven.fate.model.builder.util.FieldInjectionUtil;
import de.seven.fate.betslip.converter.Betslip2BetslipEntityConverter;
import de.seven.fate.betslip.converter.BetslipEntity2BetslipConverter;
import de.seven.fate.betslip.entity.BetslipEntity;
import de.seven.fate.betslip.exception.NoSuchEntityException;
import de.seven.fate.betslip.repository.BetslipRepository;
import de.seven.fate.betslip.vo.Betslip;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultBetslipServiceTest {

    @InjectMocks
    DefaultBetslipService sut;

    @Mock
    BetslipRepository betslipRepository;

    @Mock
    Betslip2BetslipEntityConverter betslip2BetslipEntityConverter;

    @Mock
    BetslipEntity2BetslipConverter betslipEntity2BetslipConverter;

    @Mock
    Betslip model;

    @Mock
    BetslipEntity entity;

    @Mock
    List<BetslipEntity> entities;

    @Model
    Long id;

    @Model
    String teamName;

    @Before
    public void setUp() {
        FieldInjectionUtil.injectFields(this);
    }

    @Test
    public void getTextFiles() {

        when(betslipRepository.findAllSorted()).thenReturn(entities);

        sut.getBetslips();

        verify(betslipRepository).findAllSorted();
        verify(betslipEntity2BetslipConverter).convertToList(entities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTextFileWillThrowException() {

        when(betslip2BetslipEntityConverter.convert(model)).thenReturn(entity);
        when(betslipRepository.exists(entity)).thenReturn(true);

        sut.createBetslip(model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTextFileWillThrowException() {

        when(betslip2BetslipEntityConverter.convert(model)).thenReturn(entity);
        when(betslipRepository.exists(entity)).thenReturn(false);

        sut.updateBetslip(model);
    }

    @Test
    public void updateTextFile() {

        when(betslip2BetslipEntityConverter.convert(model)).thenReturn(entity);
        when(betslipRepository.exists(entity)).thenReturn(true);
        when(betslipRepository.saveOrUpdate(entity)).thenReturn(entity);

        id = sut.updateBetslip(model);

        assertEquals(entity.getId(), id);

        verify(betslipRepository).saveOrUpdate(entity);
    }

    @Test(expected = NoSuchEntityException.class)
    public void getTextFileWillThrowException() {

        when(betslipRepository.getOne(id)).thenThrow(new NoSuchEntityException("", ""));

        sut.getBetslip(id);
    }

    @Test
    public void getTextFile() {

        when(betslipRepository.getOne(id)).thenReturn(entity);
        when(betslipEntity2BetslipConverter.convert(entity)).thenReturn(model);

        Betslip betslip = sut.getBetslip(id);

        assertEquals(model, betslip);
    }

    @Test
    public void deleteTextFile() {

        sut.deleteBetslip(id);

        verify(betslipRepository).deleteById(id);
    }

    @Test
    public void existsTextFileWillReturnWithFalse() {

        when(betslipRepository.findAllByTeamNameContainingIgnoreCase(teamName)).thenReturn(Collections.emptyList());

        boolean exists = sut.existsBetslip(teamName);

        assertFalse(exists);

        verify(betslipRepository).findAllByTeamNameContainingIgnoreCase(teamName);
    }

    @Test
    public void existsTextFile() {

        when(betslipRepository.findAllByTeamNameContainingIgnoreCase(teamName)).thenReturn(entities);

        boolean exists = sut.existsBetslip(teamName);

        assertTrue(exists);

        verify(betslipRepository).findAllByTeamNameContainingIgnoreCase(teamName);
    }
}