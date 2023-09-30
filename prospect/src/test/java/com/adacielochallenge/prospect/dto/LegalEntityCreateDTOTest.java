package com.adacielochallenge.prospect.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class LegalEntityCreateDTOTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private LegalEntityCreateDTO legalEntityCreateDTO;

    private Set<ConstraintViolation<LegalEntityCreateDTO>> violations;
    private ConstraintViolation<LegalEntityCreateDTO> violation;

    @BeforeEach
    public void setUp() {
        legalEntityCreateDTO = new LegalEntityCreateDTO();

        legalEntityCreateDTO.setCnpj("12354654463634");
        legalEntityCreateDTO.setCorporateReason("Empresa Teste");
        legalEntityCreateDTO.setName("Nome da Pessoa");
        legalEntityCreateDTO.setCpf("12345678900");
        legalEntityCreateDTO.setEmail("email@example.com");
        legalEntityCreateDTO.setMcc("3942");

        violations = null;
        violation = null;
    }

    @Test
    public void testCnpjValidation() {
        String propertyName = "cnpj";

        // valid cnpj
        legalEntityCreateDTO.setCnpj("12345678901234");
        violations = validator.validate(legalEntityCreateDTO);
        assertEquals(0, violations.size());

        // less then 14 digits
        legalEntityCreateDTO.setCnpj("1235464463634");
        violations = validator.validate(legalEntityCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals("cnpj deve conter exatamente 14 dígitos.", violation.getMessage());

        // more then 14 digits
        legalEntityCreateDTO.setCnpj("123546664463634");
        violations = validator.validate(legalEntityCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals("cnpj deve conter exatamente 14 dígitos.", violation.getMessage());

        // letters inside
        legalEntityCreateDTO.setCnpj("1235666f46g634");
        violations = validator.validate(legalEntityCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals("cnpj só pode assumir valores numéricos", violation.getMessage());

        // null
        legalEntityCreateDTO.setCnpj(null);
        violations = validator.validate(legalEntityCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals("cnpj não pode ser vazio.", violation.getMessage());
    }

    @Test
    public void testCorporateReasonValidation() {
        String propertyName = "corporateReason";

        // valid corporateReason
        legalEntityCreateDTO.setCorporateReason("Razão Social");
        violations = validator.validate(legalEntityCreateDTO);
        assertEquals(0, violations.size());

        // more than 50 characters
        legalEntityCreateDTO.setCorporateReason("jdjgususushdhsushdhsususudhujdjgususushdhsushdhsusu");
        violations = validator.validate(legalEntityCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals("razão social não pode ter mais de 50 caracteres", violation.getMessage());

        // null
        legalEntityCreateDTO.setCorporateReason(null);
        violations = validator.validate(legalEntityCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals("razão social não pode ser vazio.", violation.getMessage());

    }

    @Test
    public void testMccValidation() {
        String propertyName = "mcc";

        // valid mcc
        legalEntityCreateDTO.setMcc("1234");
        violations = validator.validate(legalEntityCreateDTO);
        assertEquals(0, violations.size());

        // more than 4 digits
        legalEntityCreateDTO.setMcc("12345");
        violations = validator.validate(legalEntityCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals("'Merchant Category Code' não deve exceder 4 caracteres.", violation.getMessage());

        // letters inside
        legalEntityCreateDTO.setMcc("12ab");
        violations = validator.validate(legalEntityCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals("'Merchant Category Code' só pode assumir valores numéricos.", violation.getMessage());

        // null
        legalEntityCreateDTO.setMcc(null);
        violations = validator.validate(legalEntityCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals("'Merchant Category Code' não pode ser vazio.", violation.getMessage());

    }

    @Test
    public void testContactCpfValidation() {
        String propertyName = "cpf";

        // valid cpf
        legalEntityCreateDTO.setCpf("12345678901");
        violations = validator.validate(legalEntityCreateDTO);
        assertEquals(0, violations.size());

        // more than 11 digits
        legalEntityCreateDTO.setCpf("123456789012");
        violations = validator.validate(legalEntityCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals("cpf deve conter exatamente 11 dígitos.", violation.getMessage());

        // less than 11 digits
        legalEntityCreateDTO.setCpf("123456789012");
        violations = validator.validate(legalEntityCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals("cpf deve conter exatamente 11 dígitos.", violation.getMessage());

        // letters inside
        legalEntityCreateDTO.setCpf("123456789ab");
        violations = validator.validate(legalEntityCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals("cpf só pode assumir valores numéricos", violation.getMessage());

        // null
        legalEntityCreateDTO.setCpf(null);
        violations = validator.validate(legalEntityCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals("cpf não pode ser vazio.", violation.getMessage());

    }

    @Test
    public void testContactNameValidation() {
        String propertyName = "name";

        // valid name
        legalEntityCreateDTO.setName("Contact Name");
        violations = validator.validate(legalEntityCreateDTO);
        assertEquals(0, violations.size());

        // more than 50 characters
        legalEntityCreateDTO.setName("jdjgususushdhsushdhsususudhujdjgususushdhsushdhsusu");
        violations = validator.validate(legalEntityCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals("nome não pode ter mais de 50 caracteres", violation.getMessage());

        // null
        legalEntityCreateDTO.setName(null);
        violations = validator.validate(legalEntityCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals("nome não pode ser vazio.", violation.getMessage());

    }

    @Test
    public void testContactEmailValidation() {
        String propertyName = "email";
        String violationMessage = "e-mail deve ser um e-mail válido";

        // valid email
        legalEntityCreateDTO.setEmail("test@example.com");
        violations = validator.validate(legalEntityCreateDTO);
        assertEquals(0, violations.size());

        // invalidating regex 1
        legalEntityCreateDTO.setEmail("emailexample.com");
        violations = validator.validate(legalEntityCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(violationMessage, violation.getMessage());

        // invalidating regex 2
        legalEntityCreateDTO.setEmail("@example.com");
        violations = validator.validate(legalEntityCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(violationMessage, violation.getMessage());

        // invalidating regex 3
        legalEntityCreateDTO.setEmail("test@.com");
        violations = validator.validate(legalEntityCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(violationMessage, violation.getMessage());

        // invalidating regex 4
        legalEntityCreateDTO.setEmail("test@example.");
        violations = validator.validate(legalEntityCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(violationMessage, violation.getMessage());

        // invalidating regex 5
        legalEntityCreateDTO.setEmail("test@examplecom");
        violations = validator.validate(legalEntityCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(violationMessage, violation.getMessage());

        // invalidating regex 6
        legalEntityCreateDTO.setEmail("test@example.c");
        violations = validator.validate(legalEntityCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals(violationMessage, violation.getMessage());

        // null
        legalEntityCreateDTO.setEmail(null);
        violations = validator.validate(legalEntityCreateDTO);
        violation = violations.iterator().next();
        assertEquals(1, violations.size());
        assertEquals(propertyName, violation.getPropertyPath().toString());
        assertEquals("e-mail não pode ser vazio.", violation.getMessage());

    }

}
