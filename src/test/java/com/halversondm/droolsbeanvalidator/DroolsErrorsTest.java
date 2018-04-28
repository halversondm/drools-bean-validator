package com.halversondm.droolsbeanvalidator;

import com.halversondm.rules.Errors;
import com.halversondm.rules.Person;
import org.drools.core.event.DebugAgendaEventListener;
import org.drools.core.event.DebugRuleRuntimeEventListener;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DroolsErrorsTest {

    private static Logger LOGGER = LoggerFactory.getLogger(DroolsErrorsTest.class);

    KieServices kieServices = KieServices.Factory.get();
    KieContainer kContainer = kieServices.getKieClasspathContainer();
    StatelessKieSession ksession;

    @Before
    public void setUp() {
        ksession = kContainer.newStatelessKieSession("validation");
        ksession.addEventListener(new DebugAgendaEventListener());
        ksession.addEventListener(new DebugRuleRuntimeEventListener());
    }

    @Test
    public void validate_WhenInvalidAge() {
        Person person = new Person();
        person.setName("Ryan");

        Errors errors = new Errors();
        List<Object> facts = Arrays.asList(errors, person);
        ksession.execute(facts);

        assertTrue(errors.hasErrors());
        errors.getErrors().forEach((error) -> LOGGER.info("{}",error));
    }

    @Test
    public void validate_WhenValidPerson() {
        Person person = new Person();
        person.setName("Ryan");
        person.setAge(11);

        Errors errors = new Errors();
        List<Object> facts = Arrays.asList(errors, person);
        ksession.execute(facts);

        assertFalse(errors.hasErrors());
    }

    @Test
    public void validate_WhenInvalidName() {
        Person person = new Person();
        person.setAge(11);

        Errors errors = new Errors();
        List<Object> facts = Arrays.asList(errors, person);
        ksession.execute(facts);

        assertTrue(errors.hasErrors());
        errors.getErrors().forEach((error) -> LOGGER.info("{}",error));
    }

    @Test
    public void validate_WhenInvalidNameAndAge() {
        Person person = new Person();

        Errors errors = new Errors();
        List<Object> facts = Arrays.asList(errors, person);
        ksession.execute(facts);

        assertTrue(errors.hasErrors());
        assertEquals(2, errors.getErrors().size());
        errors.getErrors().forEach((error) -> LOGGER.info("{}",error));
    }



}
