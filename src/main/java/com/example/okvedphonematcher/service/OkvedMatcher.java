package com.example.okvedphonematcher.service;

import com.example.okvedphonematcher.domain.OkvedNode;
import com.example.okvedphonematcher.domain.OkvedData;
import com.example.okvedphonematcher.domain.PhoneNumber;

/**
 * Поиск ОКВЭД
 */
public interface OkvedMatcher {

    /**
     * Найти ОКВЭД который соответствует последним цифрам номера телефона
     *
     * @param data  данные ОКВЭД
     * @param phone нормализованный номер телефона
     * @return найденный ОКВЭД
     */
    OkvedNode matchByPhone(OkvedData data, PhoneNumber phone);
}
