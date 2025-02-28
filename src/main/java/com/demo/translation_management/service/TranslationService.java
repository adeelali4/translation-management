package com.demo.translation_management.service;

import com.demo.translation_management.base.dto.request.TranslationRequestDTO;
import com.demo.translation_management.base.dto.response.TranslationResponseDTO;
import com.demo.translation_management.persistence.entity.Translation;

import java.util.List;

public interface TranslationService {
    List<TranslationResponseDTO> findAll() throws Exception;
    TranslationResponseDTO findById(Long id) throws Exception;
    TranslationResponseDTO update(Long id, TranslationRequestDTO translation) throws Exception;
    TranslationResponseDTO save(TranslationRequestDTO translation) throws Exception;
    void delete(Long id);
    List<TranslationResponseDTO> search(Long localeId, Long tagId, String key) throws Exception;
    void generate(int number) throws Exception;
}
