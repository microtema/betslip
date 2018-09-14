package it.de.seven.fate.texteditor.repository;

import de.seven.fate.betslip.BetslipServerApplication;
import de.seven.fate.betslip.entity.BetslipEntity;
import de.seven.fate.betslip.repository.BetslipRepository;
import de.seven.fate.model.builder.annotation.Models;
import de.seven.fate.model.builder.util.CollectionUtil;
import de.seven.fate.model.builder.util.FieldInjectionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@DirtiesContext
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BetslipServerApplication.class)
public class BetslipRepositoryIT {

    @Autowired
    BetslipRepository sut;

    @Models
    List<BetslipEntity> models;

    @Before
    public void setUp() {

        FieldInjectionUtil.injectFields(this);

        sut.saveAll(models);
    }

    @After
    public void tearDown() {

        sut.deleteAll();
    }

    @Test
    public void findAllSorted() {

        List<BetslipEntity> entities = sut.findAllSorted();

        assertEquals(models.size(), entities.size());

        List<String> expectedTeamNames = models.stream().map(BetslipEntity::getTeamName).sorted().collect(Collectors.toList());
        List<String> currentTeamNames = entities.stream().map(BetslipEntity::getTeamName).sorted().collect(Collectors.toList());

        assertEquals(expectedTeamNames, currentTeamNames);
    }

    @Test
    public void findAllByTeamNameContainingIgnoreCase() {

        BetslipEntity model = CollectionUtil.random(models);

        String teamName = model.getTeamName();
        String searchTerm = teamName.substring(2, 10);
        List<BetslipEntity> entities = sut.findAllByTeamNameContainingIgnoreCase(searchTerm);

        assertFalse(entities.isEmpty());
        entities.forEach(it -> assertTrue(it .getTeamName().contains(searchTerm)));
    }
}