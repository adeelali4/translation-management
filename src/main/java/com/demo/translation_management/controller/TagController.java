package com.demo.translation_management.controller;

import com.demo.translation_management.persistence.entity.Tag;
import com.demo.translation_management.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
class TagController {
    @Autowired
    private TagService service;

    @GetMapping
    public List<Tag> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Tag create(@RequestBody Tag tag) {
        return service.save(tag);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
