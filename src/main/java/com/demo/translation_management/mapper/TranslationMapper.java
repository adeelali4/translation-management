package com.demo.translation_management.mapper;

import com.demo.translation_management.base.dto.request.TranslationRequestDTO;
import com.demo.translation_management.base.dto.response.TranslationResponseDTO;
import com.demo.translation_management.persistence.entity.Locale;
import com.demo.translation_management.persistence.entity.Tag;
import com.demo.translation_management.persistence.entity.Translation;
import com.demo.translation_management.persistence.repository.LocaleRepository;
import com.demo.translation_management.persistence.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class TranslationMapper {
    @Autowired
    LocaleRepository localeRepository;

    @Autowired
    TagRepository tagRepository;

    public Translation add(TranslationRequestDTO model) throws Exception {
        Translation translation = new Translation();
        translation.setKey(model.getKey());
        translation.setValue(model.getValue());

        Locale locale = localeRepository.findById(model.getLocaleId()).orElse(null);
        translation.setLocale(locale);

        Tag tag = tagRepository.findById(model.getTagId()).orElse(null);
        translation.setTag(tag);
        return translation;
    }

    public TranslationResponseDTO find(Translation translation) throws Exception {
        TranslationResponseDTO response = new TranslationResponseDTO();
        response.setId(translation.getId());
        response.setKey(translation.getKey());
        response.setValue(translation.getValue());
        response.setTag(translation.getTag().getName());
        response.setLocale(translation.getLocale().getName());
        return response;
    }

    public List<TranslationResponseDTO> findAll(List<Translation> translations) throws Exception {
        return translations.stream().map(translation -> {
                    try {
                        return find(translation);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Translation update(TranslationRequestDTO model, Translation translation) throws Exception {
        if (model.getKey() != null) {
            translation.setKey(model.getKey());
        }

        if (model.getValue() != null) {
            translation.setValue(model.getValue());
        }

        if (model.getLocaleId() != null) {
            Locale locale = localeRepository.findById(model.getLocaleId()).orElse(null);
            translation.setLocale(locale);
        }

        if (model.getTagId() != null) {
            Tag tag = tagRepository.findById(model.getTagId()).orElse(null);
            translation.setTag(tag);
        }
        return translation;
    }
}
