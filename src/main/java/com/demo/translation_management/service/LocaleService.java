package com.demo.translation_management.service;

import com.demo.translation_management.persistence.entity.Locale;

import java.util.List;

public interface LocaleService {
    List<Locale> findAll();
    Locale save(Locale locale);
    void delete(Long id);
}
