package com.example.okvedphonematcher.service.impl;

import com.example.okvedphonematcher.exception.InvalidMobilePhoneNumberException;
import com.example.okvedphonematcher.service.PhoneNormalizer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringJUnitConfig(classes = PhoneNormalizationService.class)
class PhoneNormalizerTest {

    @Autowired
    PhoneNormalizer phoneNormalizer;

    @CsvSource(
            {
                    "+7 (912) 345-67-89, +79123456789",
                    "8 912 345 67 89, +79123456789",
                    "79123456789, +79123456789",
                    "9123456789, +79123456789",
                    "+79123456789, +79123456789",
                    "7-912-345-67-89, +79123456789"
            }
    )
    @ParameterizedTest
    void normalize(String rawNumber, String expectedResult) {
        assertEquals(expectedResult, phoneNormalizer.normalize(rawNumber).value());
    }

    @CsvSource(
            {
                    "123",
                    "+1 912 345 6789",
                    "+8 912 345 6789",
                    "9 912 345 6789",
            }
    )
    @ParameterizedTest
    void normalize_fail(String rawNumber) {
        assertThrows(InvalidMobilePhoneNumberException.class, () -> phoneNormalizer.normalize(rawNumber));
    }
}