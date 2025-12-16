package com.example.okvedphonematcher.service;

import com.example.okvedphonematcher.domain.OkvedData;
import com.example.okvedphonematcher.domain.OkvedNode;
import com.example.okvedphonematcher.domain.PhoneNumber;

/**
 * Резервная стратегия поиска ОКВЭД
 */
public interface FallbackStrategy {

    /**
     * Найти ОКВЭД который соответствует первым цифрам номера телефона без кода страны
     *
     * @param phone нормализованный номер телефона
     * @param data  данные ОКВЭД
     * @return найденный ОКВЭД
     */
    OkvedNode match(PhoneNumber phone, OkvedData data);
}
