package com.github.pieter_groenendijk.domain.shared.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class EmailValidatorTest {

    @Test
    void testValidEmail() {
        assertTrue(EmailValidator.isValidEmail("test@example.com"));
    }

    @Test
    void testInvalidEmailMissingAtSymbol() {
        assertFalse(EmailValidator.isValidEmail("testexample.com"));
    }

    @Test
    void testInvalidEmailMissingDomain() {
        assertFalse(EmailValidator.isValidEmail("test@"));
    }

    @Test
    void testInvalidEmailWithSpecialChars() {
        assertFalse(EmailValidator.isValidEmail("test@exam!ple.com"));
    }

    @Test
    void testValidEmailWithSubdomain() {
        assertTrue(EmailValidator.isValidEmail("test@sub.example.com"));
    }


    @Test
    void testEmptyEmail() {
        assertFalse(EmailValidator.isValidEmail(""));
    }


}
