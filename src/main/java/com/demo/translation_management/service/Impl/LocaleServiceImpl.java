package com.demo.translation_management.service.Impl;

import com.demo.translation_management.persistence.entity.Locale;
import com.demo.translation_management.persistence.repository.LocaleRepository;
import com.demo.translation_management.service.LocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocaleServiceImpl implements LocaleService {
    @Autowired
    private LocaleRepository localeRepository;

    public List<Locale> findAll() {
        return localeRepository.findAll();
    }

    public Locale save(Locale locale) {
        return localeRepository.save(locale);
    }

    public void delete(Long id) {
        localeRepository.deleteById(id);
    }
}
