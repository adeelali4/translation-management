package com.demo.translation_management.base.dto.request;

import lombok.Data;

@Data
public class TranslationRequestDTO {
    private Long localeId;
    private Long tagId;
    private String key;
    private String value;
}
