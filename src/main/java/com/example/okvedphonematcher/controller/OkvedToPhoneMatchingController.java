package com.example.okvedphonematcher.controller;

import com.example.okvedphonematcher.controller.dto.OkvedToPhoneMatchResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Поиск ОКВЭД по номеру телефона")
public interface OkvedToPhoneMatchingController {

    @Operation(summary = "Найти ОКВЭД по номеру телефона")
    OkvedToPhoneMatchResult match(
            @Schema(
                    description = "Номер мобильного телефона",
                    requiredMode = Schema.RequiredMode.REQUIRED
            )
            String phone
    );
}
