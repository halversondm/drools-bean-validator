package com.halversondm.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=BusinessRulesConstraintValidator.class)
public @interface BusinessRulesConstraint {

    String message() default "Business rules validation failed.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
