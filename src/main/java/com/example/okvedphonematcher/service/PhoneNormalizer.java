package com.example.okvedphonematcher.service;

import com.example.okvedphonematcher.domain.PhoneNumber;

/**
 * Нормализация мобильного номера телефона
 */
public interface PhoneNormalizer {

    /**
     * Нормализовать мобильный номер телефона
     *
     * @param rawNumber Номер телефона для нормализации
     * @return Нормализованный номер телефона
     */
    PhoneNumber normalize(String rawNumber);
}
