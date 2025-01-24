package com.github.pieter_groenendijk.domain.shared.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenderCheckTest {
    @Test
    void testValidMaleGender() {
        assertTrue(GenderCheck.exists('M'));
    }

    @Test
    void testValidFemaleGender() {
        assertTrue(GenderCheck.exists('F'));
    }

    @Test
    void testValidOtherGender() {
        assertTrue(GenderCheck.exists('O'));
    }

    @Test
    void testInvalidGender() {
        assertFalse(GenderCheck.exists('X'));
    }

    @Test
    void testEmptyGenderCode() {
        assertFalse(GenderCheck.exists(' '));
    }

    @Test
    void testNonLetterGenderCode() {
        assertFalse(GenderCheck.exists('1'));
    }

    @Test
    void testNullGenderCode() {
        assertFalse(GenderCheck.exists('\0'));
    }
}