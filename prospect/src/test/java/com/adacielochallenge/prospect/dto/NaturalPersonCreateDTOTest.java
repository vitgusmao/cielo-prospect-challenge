package com.adacielochallenge.prospect.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class NaturalPersonCreateDTOTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private NaturalPersonCreateDTO naturalPersonCreateDTO;

    private Set<ConstraintViolation<NaturalPersonCreateDTO>> violations;
    private ConstraintViolation<NaturalPersonCreateDTO> violation;

    private final String nullViolationMessage = "must not be null";

    @BeforeEach
    public void setUp() {
        naturalPersonCreateDTO = new NaturalPersonCreateDTO();

        naturalPersonCreateDTO.setName("Nome da Pessoa");
        naturalPersonCreateDTO.setCpf("12345678900");
        naturalPersonCreateDTO.setEmail("email@example.com");
        naturalPersonCreateDTO.setMcc("3942");

        violations = null;
        violation = null;
    }

    @Test
    public void testCpfValidation() {
        String propertyName = "cpf";
        String violationMessage = "cpf must have 11 digits";
        Set<ConstraintViolation<NaturalPersonCreateDTO>> violations;
        ConstraintViolation<NaturalPersonCreateDTO> violation;

        // valid cpf
        naturalPersonCreateDTO.setCpf("12345678901");
        violations = validator.validate(naturalPersonCreateDTO);
        assertEquals(0, violations.size());

        // more than 11 digits
        naturalPersonCreateDTO.setCpf("123456789012");
        violations = validator.validate(naturalPersonCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(violationMessage, violation.getMessage());

        // less than 11 digits
        naturalPersonCreateDTO.setCpf("123456789012");
        violations = validator.validate(naturalPersonCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(violationMessage, violation.getMessage());

        // letters inside
        naturalPersonCreateDTO.setCpf("123456789ab");
        violations = validator.validate(naturalPersonCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(violationMessage, violation.getMessage());

        // null
        naturalPersonCreateDTO.setCpf(null);
        violations = validator.validate(naturalPersonCreateDTO);
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(violationMessage, violation.getMessage());

    }

    @Test
    public void testMccValidation() {
        String propertyName = "mcc";
        String violationMessage = "'Merchant Category Code' must not exceed 4 digits";

        // valid mcc
        naturalPersonCreateDTO.setMcc("1234");
        violations = validator.validate(naturalPersonCreateDTO);
        assertEquals(0, violations.size());

        // more than 4 digits
        naturalPersonCreateDTO.setMcc("12345");
        violations = validator.validate(naturalPersonCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(violationMessage, violation.getMessage());

        // letters inside
        naturalPersonCreateDTO.setMcc("12ab");
        violations = validator.validate(naturalPersonCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(violationMessage, violation.getMessage());

        // null
        naturalPersonCreateDTO.setMcc(null);
        violations = validator.validate(naturalPersonCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(nullViolationMessage, violation.getMessage());

    }

    @Test
    public void testNameValidation() {
        String propertyName = "name";
        String violationMessage = "name must not exceed 50 characters";

        // valid name
        naturalPersonCreateDTO.setName("Natural Person Name");
        violations = validator.validate(naturalPersonCreateDTO);
        assertEquals(0, violations.size());

        // more than 50 characters
        naturalPersonCreateDTO.setName("jdjgususushdhsushdhsususudhujdjgususushdhsushdhsusu");
        violations = validator.validate(naturalPersonCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(violationMessage, violation.getMessage());

        // null
        naturalPersonCreateDTO.setName(null);
        violations = validator.validate(naturalPersonCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(nullViolationMessage, violation.getMessage());

    }

    @Test
    public void testEmailValidation() {
        String propertyName = "email";
        String violationMessage = "email must be a valid e-mail";

        // valid email
        naturalPersonCreateDTO.setEmail("test@example.com");
        violations = validator.validate(naturalPersonCreateDTO);
        assertEquals(0, violations.size());

        // invalidating regex 1
        naturalPersonCreateDTO.setEmail("emailexample.com");
        violations = validator.validate(naturalPersonCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(violationMessage, violation.getMessage());

        // invalidating regex 2
        naturalPersonCreateDTO.setEmail("@example.com");
        violations = validator.validate(naturalPersonCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(violationMessage, violation.getMessage());

        // invalidating regex 3
        naturalPersonCreateDTO.setEmail("test@.com");
        violations = validator.validate(naturalPersonCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(violationMessage, violation.getMessage());

        // invalidating regex 4
        naturalPersonCreateDTO.setEmail("test@example.");
        violations = validator.validate(naturalPersonCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(violationMessage, violation.getMessage());

        // invalidating regex 5
        naturalPersonCreateDTO.setEmail("test@examplecom");
        violations = validator.validate(naturalPersonCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(violationMessage, violation.getMessage());

        // invalidating regex 6
        naturalPersonCreateDTO.setEmail("test@example.c");
        violations = validator.validate(naturalPersonCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(violationMessage, violation.getMessage());

        // null
        naturalPersonCreateDTO.setEmail(null);
        violations = validator.validate(naturalPersonCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(nullViolationMessage, violation.getMessage());

    }

}
