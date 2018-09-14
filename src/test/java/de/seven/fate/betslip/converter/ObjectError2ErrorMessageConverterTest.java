package de.seven.fate.betslip.converter;

import de.seven.fate.model.builder.annotation.Model;
import de.seven.fate.model.builder.util.FieldInjectionUtil;
import de.seven.fate.betslip.vo.ErrorMessage;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ObjectError2ErrorMessageConverterTest {

    @Autowired
    ObjectError2ErrorMessageConverter sut;

    @Model
    FieldError model;

    @Before
    public void setUp() {
        FieldInjectionUtil.injectFields(this);
    }

    @Test
    public void convert() {

        ErrorMessage entity = sut.convert(model);

        assertNotNull(entity);

        assertEquals(model.getDefaultMessage(), entity.getDefaultMessage());
        assertEquals(model.getField(), entity.getPropertyName());
        assertEquals(model.getObjectName(), entity.getObjectName());
    }

    @Test
    public void createInstance() {

        assertNotNull(sut.createInstance());
    }
}