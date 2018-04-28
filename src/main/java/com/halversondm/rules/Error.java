package com.halversondm.rules;

/**
 * Represents one error of an object in a bean validation.
 */
public class Error {

    private Object target;
    private String field;
    private String message;

    public Error(Object target, String field, String message) {
        this.target = target;
        this.field = field;
        this.message = message;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Error{" +
                "target=" + target +
                ", field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
