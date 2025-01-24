package com.github.pieter_groenendijk.domain.shared.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class EmailValidatorTest {

    @Test
    void testValidEmail() {
        // Valid email example
        assertTrue(EmailValidator.isValidEmail("test@example.com"));
    }

    @Test
    void testInvalidEmailMissingAtSymbol() {
        // Invalid email without '@'
        assertFalse(EmailValidator.isValidEmail("testexample.com"));
    }

    @Test
    void testInvalidEmailMissingDomain() {
        // Invalid email without domain part after '@'
        assertFalse(EmailValidator.isValidEmail("test@"));
    }

    @Test
    void testInvalidEmailWithSpecialChars() {
        // Invalid email with special characters
        assertFalse(EmailValidator.isValidEmail("test@exam!ple.com"));
    }

    @Test
    void testValidEmailWithSubdomain() {
        // Valid email with subdomain
        assertTrue(EmailValidator.isValidEmail("test@sub.example.com"));
    }


    @Test
    void testEmptyEmail() {
        // Invalid email that is empty
        assertFalse(EmailValidator.isValidEmail(""));
    }


}
