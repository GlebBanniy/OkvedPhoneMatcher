package com.example.okvedphonematcher.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * ОКВЭД
 *
 * @param code  Код ОКВЭД
 * @param name  Название ОКВЭД
 * @param items Внутренний раздел ОКВЭД
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record OkvedNode(
        @JsonProperty("code")
        String code,

        @JsonProperty("name")
        String name,

        @JsonProperty("items")
        List<OkvedNode> items
) {

}
