package com.example.okvedphonematcher.domain;

/**
 * Лучшее найденное совпадение с ОКВЭД
 *
 * @param node        Найденный ОКВЭД
 * @param matchLength Длина совпадения
 */
public record BestMatchOkved(
        OkvedNode node,
        int matchLength
) {
}
