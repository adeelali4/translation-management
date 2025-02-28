package com.demo.translation_management.controller;

import com.demo.translation_management.persistence.entity.Locale;
import com.demo.translation_management.service.LocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locale")
class LocaleController {
    @Autowired
    private LocaleService service;

    @GetMapping
    public List<Locale> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Locale create(@RequestBody Locale locale) {
        return service.save(locale);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
