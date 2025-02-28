package com.demo.translation_management.persistence.repository;

import com.demo.translation_management.persistence.entity.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslationRepository  extends JpaRepository<Translation, Long> {
    List<Translation> findByLocaleIdAndTagIdAndKey(Long localeId, Long tagId, String key);
}
