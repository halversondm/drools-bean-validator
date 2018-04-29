package com.halversondm.validation;

import com.halversondm.rules.Error;
import com.halversondm.rules.Errors;
import org.drools.core.event.DebugAgendaEventListener;
import org.drools.core.event.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * Using JSR-303 based Validation instead of a defined service.
 */
@Service
public class BusinessRulesConstraintValidator implements ConstraintValidator<BusinessRulesConstraint, Object> {

    @Autowired
    KieContainer kieContainer;

    @Override
    public void initialize(BusinessRulesConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        StatelessKieSession ksession = kieContainer.newStatelessKieSession("validation");
        ksession.addEventListener(new DebugAgendaEventListener());
        ksession.addEventListener(new DebugRuleRuntimeEventListener());
        Errors errors = new Errors();
        List<Object> facts = Arrays.asList(errors, object);
        ksession.execute(facts);
        if (errors.hasErrors()) {
            context.disableDefaultConstraintViolation();
            for (Error error : errors.getErrors()) {
                context.buildConstraintViolationWithTemplate(error.getMessage()).addPropertyNode(error.getField()).addConstraintViolation();
            }
            return false;
        }

        return true;
    }
}
