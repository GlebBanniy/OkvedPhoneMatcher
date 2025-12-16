package com.example.okvedphonematcher.domain;

import java.util.List;

/**
 * Данные ОКВЭД
 *
 * @param items Внутренний раздел ОКВЭД
 */
public record OkvedData(
        List<OkvedNode> items
) {
}
