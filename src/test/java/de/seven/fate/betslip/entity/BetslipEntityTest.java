package de.seven.fate.betslip.entity;

import de.seven.fate.model.builder.annotation.Model;
import de.seven.fate.model.builder.util.FieldInjectionUtil;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class BetslipEntityTest {

    @Model
    BetslipEntity sut;

    @Before
    public void setUp() {
        FieldInjectionUtil.injectFields(this);
    }

    @Test
    public void isAnnotatedWithEntity() {

        assertTrue(BetslipEntity.class.isAnnotationPresent(Entity.class));
    }

    @Test
    public void isAnnotatedWithTable() {

        assertTrue(BetslipEntity.class.isAnnotationPresent(Table.class));
    }

    @Test
    public void testTableName() {

        Table annotation = BetslipEntity.class.getAnnotation(Table.class);

        assertEquals("BETSLIP", annotation.name());
    }

    @Test
    public void testUniqueConstraints() {

        Table annotation = BetslipEntity.class.getAnnotation(Table.class);

        UniqueConstraint[] uniqueConstraints = annotation.uniqueConstraints();
        assertEquals(1, uniqueConstraints.length);
        assertArrayEquals(new String[]{"userId", "teamName"}, uniqueConstraints[0].columnNames());
    }

    @Test
    public void testNotEquals() {

        BetslipEntity other = new BetslipEntity();

        other.setTeamName(sut.getTeamName() + ".changed");
        other.setAmount(sut.getAmount());

        assertNotEquals(sut, other);
    }
}