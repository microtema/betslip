package it.de.seven.fate.texteditor.resource;

import de.seven.fate.betslip.entity.BetslipEntity;
import de.seven.fate.betslip.vo.Betslip;
import de.seven.fate.model.builder.annotation.Model;
import de.seven.fate.model.builder.annotation.Models;
import de.seven.fate.model.builder.util.CollectionUtil;
import de.seven.fate.model.builder.util.FieldInjectionUtil;
import de.seven.fate.betslip.BetslipServerApplication;
import de.seven.fate.betslip.converter.Betslip2BetslipEntityConverter;
import de.seven.fate.betslip.converter.BetslipEntity2BetslipConverter;
import de.seven.fate.betslip.repository.BetslipRepository;
import de.seven.fate.betslip.resource.BetslipResource;
import de.seven.fate.betslip.vo.ErrorMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BetslipServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BetslipResourceIT {

    @Autowired
    BetslipResource sut;

    @Autowired
    BetslipRepository betslipRepository;

    @Autowired
    Betslip2BetslipEntityConverter betslip2BetslipEntityConverter;

    @Autowired
    BetslipEntity2BetslipConverter betslipEntity2BetslipConverter;

    @Autowired
    TestRestTemplate restTemplate;

    String PATH = "/betslip";

    @Models
    List<Betslip> models;

    @Model
    Betslip model;

    ParameterizedTypeReference<List<Betslip>> typeReference = new ParameterizedTypeReference<List<Betslip>>() {
    };

    ParameterizedTypeReference<List<ErrorMessage>> typeReferenceObjectError = new ParameterizedTypeReference<List<ErrorMessage>>() {
    };

    @Before
    public void setUp() {

        FieldInjectionUtil.injectFields(this);

        List<BetslipEntity> entities = betslip2BetslipEntityConverter.convertToList(models);

        betslipRepository.saveAll(entities);

        betslipEntity2BetslipConverter.update(entities, models);
    }

    @After
    public void tearDown() {
        betslipRepository.deleteAll();
    }

    @Test
    public void query() {

        //when
        ResponseEntity<List<Betslip>> response = restTemplate.exchange(PATH, HttpMethod.GET, null, typeReference);

        //then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Betslip> entries = response.getBody();

        assertNotNull(entries);
        assertEquals(models.size(), entries.size());
    }

    @Test
    public void createBetslipWillReturnWithBadRequest() {

        //given
        model.setTeamName(null);

        //when
        ResponseEntity<List<ErrorMessage>> response = restTemplate.exchange(PATH, HttpMethod.POST, new HttpEntity<>(model), typeReferenceObjectError);

        //then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        List<ErrorMessage> responseBody = response.getBody();

        assertNotNull(responseBody);

        assertEquals(1, responseBody.size());
        assertEquals("betslip", responseBody.get(0).getObjectName());
        assertEquals("teamName", responseBody.get(0).getPropertyName());
        assertEquals("darf nicht null sein", responseBody.get(0).getDefaultMessage());
    }

    @Test
    public void create() {

        //when
        ResponseEntity<Long> response = restTemplate.exchange(PATH, HttpMethod.POST, new HttpEntity<>(model), ParameterizedTypeReference.forType(Long.class));

        //then
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Long id = response.getBody();

        assertNotNull(id);
    }

    @Test
    public void update() {

        //given
        model = CollectionUtil.random(models);

        model.setTeamName(model.getTeamName() + ".changed");

        //when
        ResponseEntity<Long> response = restTemplate.exchange(PATH, HttpMethod.PUT, new HttpEntity<>(model), ParameterizedTypeReference.forType(Long.class));

        //then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Long id = response.getBody();

        assertNotNull(id);
        assertEquals(model.getId(), id);
    }

    @Test
    public void delete() {

        //given
        model= CollectionUtil.random(models);

        //when
        ResponseEntity response = restTemplate.exchange(PATH + "/" + model.getId(), HttpMethod.DELETE, null, ParameterizedTypeReference.forType(Void.class));

        //then
        assertNotNull(response);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }
}