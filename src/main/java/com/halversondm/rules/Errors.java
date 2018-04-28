package com.halversondm.rules;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Represents all errors for a given bean validation
 */
public class Errors {

    private final List<Error> errors = Collections.synchronizedList(new ArrayList<>());

    public Collection<Error> getErrors() {
        return Collections.unmodifiableCollection(errors);
    }

    public void addError(Object target, String field, String message) {
        this.errors.add(new Error(target, field, message));
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
