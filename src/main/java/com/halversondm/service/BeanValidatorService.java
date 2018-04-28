package com.halversondm.service;

import com.halversondm.rules.Errors;
import org.drools.core.event.DebugAgendaEventListener;
import org.drools.core.event.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BeanValidatorService {

    @Autowired
    KieContainer kieContainer;

    public Errors validate(Object object) {
        StatelessKieSession ksession = kieContainer.newStatelessKieSession("validation");
        ksession.addEventListener(new DebugAgendaEventListener());
        ksession.addEventListener(new DebugRuleRuntimeEventListener());
        Errors errors = new Errors();
        List<Object> facts = Arrays.asList(errors, object);
        ksession.execute(facts);
        return errors;
    }
}
