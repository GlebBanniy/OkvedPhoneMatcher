package com.example.okvedphonematcher.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@Schema(description = "Ошибка при обработке запроса")
public final class ErrorResponse {

    @Schema(description = "Код")
    private int code;

    @Schema(description = "Причина")
    private String description;
}

