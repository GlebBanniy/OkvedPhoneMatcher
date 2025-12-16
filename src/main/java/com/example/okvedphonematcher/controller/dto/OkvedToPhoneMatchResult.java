package com.example.okvedphonematcher.controller.dto;

import com.example.okvedphonematcher.domain.OkvedNode;
import com.example.okvedphonematcher.domain.PhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Результат поиска ОКВЭД")
public record OkvedToPhoneMatchResult(

        @Schema(description = "Номер телефона")
        String phone,

        @Schema(description = "Код ОКВЭД")
        String code,

        @Schema(description = "Наименование ОКВЭД")
        String name
) {
    public static OkvedToPhoneMatchResult of(PhoneNumber number, OkvedNode node) {
        return new OkvedToPhoneMatchResult(number.value(), node.code(), node.name());
    }
}
