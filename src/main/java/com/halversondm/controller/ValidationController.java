package com.halversondm.controller;

import com.halversondm.rules.Errors;
import com.halversondm.rules.Person;
import com.halversondm.service.BeanValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/validation")
public class ValidationController {

    @Autowired
    BeanValidatorService beanValidatorService;

    @RequestMapping(value = "/validateWithService", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Errors validateWithService(@RequestBody Person person) {

        return beanValidatorService.validate(person);

    }

    @RequestMapping(value = "/validateWithJSR303", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void validateWithJSR303(@Valid @RequestBody Person person) {
    }
}
