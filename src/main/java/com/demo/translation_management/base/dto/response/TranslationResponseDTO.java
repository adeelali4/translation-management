package com.demo.translation_management.base.dto.response;

import lombok.Data;

@Data
public class TranslationResponseDTO {
    private Long id;
    private String locale;
    private String tag;
    private String key;
    private String value;
}
