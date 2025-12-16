package com.example.okvedphonematcher.controller.dto;

import com.example.okvedphonematcher.domain.BestMatchOkved;
import com.example.okvedphonematcher.domain.PhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Результат поиска ОКВЭД")
public record OkvedToPhoneMatchResult(

        @Schema(description = "Номер телефона")
        String phone,

        @Schema(description = "Код ОКВЭД")
        String code,

        @Schema(description = "Наименование ОКВЭД")
        String name,

        @Schema(description = "Длина совпадения")
        int matchLength
) {
    public static OkvedToPhoneMatchResult of(PhoneNumber number, BestMatchOkved okved) {
        return new OkvedToPhoneMatchResult(
                number.value(),
                okved.node().code(),
                okved.node().name(),
                okved.matchLength()
        );
    }
}
