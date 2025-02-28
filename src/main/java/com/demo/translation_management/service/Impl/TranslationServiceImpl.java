package com.demo.translation_management.service.Impl;

import com.demo.translation_management.base.dto.request.TranslationRequestDTO;
import com.demo.translation_management.base.dto.response.TranslationResponseDTO;
import com.demo.translation_management.mapper.TranslationMapper;
import com.demo.translation_management.persistence.entity.Locale;
import com.demo.translation_management.persistence.entity.Tag;
import com.demo.translation_management.persistence.entity.Translation;
import com.demo.translation_management.persistence.repository.LocaleRepository;
import com.demo.translation_management.persistence.repository.TagRepository;
import com.demo.translation_management.persistence.repository.TranslationRepository;
import com.demo.translation_management.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TranslationServiceImpl implements TranslationService {
    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private LocaleRepository localeRepository;

    @Autowired
    private TranslationMapper translationMapper;

    private List<TranslationResponseDTO> translationResponseDTOs = new ArrayList<>();
    private boolean newRecord = true;


    public List<TranslationResponseDTO> findAll() throws Exception {
        if (newRecord) {
            List<Translation> translations = translationRepository.findAll();
            translationResponseDTOs = translationMapper.findAll(translations);
            newRecord = false;
        }

        return translationResponseDTOs;
    }

    public TranslationResponseDTO findById(Long id) throws Exception {
        Translation translation = translationRepository.findById(id).orElse(null);
        if (translation == null) {
            throw new Exception("Translation not found.");
        }
        return translationMapper.find(translation);
    }

    public TranslationResponseDTO update(Long id, TranslationRequestDTO translation) throws Exception {
        Translation translationEntity = translationRepository.findById(id).orElse(null);
        if (translationEntity == null) {
            throw new Exception("Translation not found.");
        }

        translationMapper.update(translation, translationEntity);
        translationRepository.save(translationEntity);

        TranslationResponseDTO response = translationMapper.find(translationEntity);

        translationResponseDTOs.stream()
                .filter(dto -> dto.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(
                        existing -> translationResponseDTOs.set(translationResponseDTOs.indexOf(existing), response),
                        () -> translationResponseDTOs.add(response)
                );
        return response;
    }

    public TranslationResponseDTO save(TranslationRequestDTO translation) throws Exception {
        Translation translationEntity = translationMapper.add(translation);
        translationRepository.save(translationEntity);

        TranslationResponseDTO response = translationMapper.find(translationEntity);
        translationResponseDTOs.add(response);
        return response;
    }

    public void delete(Long id) {
        translationRepository.deleteById(id);
    }

    public List<TranslationResponseDTO> search(Long localeId, Long tagId, String key) throws Exception {
        List<Translation> translationList = translationRepository.findByLocaleIdAndTagIdAndKey(localeId, tagId, key);
        return translationMapper.findAll(translationList);
    }

    public void generate(int number) throws Exception {
        List<Translation> translations = new ArrayList<>();
        List<Tag> tag = tagRepository.findAll();
        List<Locale> locale = localeRepository.findAll();

        if(tag.isEmpty() || locale.isEmpty()) {
            throw new Exception("Tag or Locale not found");
        }

        int batchSize = 10000;

        for (int i = 0; i < number; i++) {
            Translation translation = new Translation();
            translation.setLocale(locale.get(0));
            translation.setTag(tag.get(0));
            translation.setKey("Key_" + i);
            translation.setValue("Value " + i);
            translations.add(translation);

            if (translations.size() >= batchSize) {
                translationRepository.saveAll(translations);
                translationResponseDTOs.addAll(translationMapper.findAll(translations));
                translations.clear();
            }
        }

        if (!translations.isEmpty()) {
            translationRepository.saveAll(translations);
            translationResponseDTOs.addAll(translationMapper.findAll(translations));
        }
    }
}
