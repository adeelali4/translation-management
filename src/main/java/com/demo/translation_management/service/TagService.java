package com.demo.translation_management.service;

import com.demo.translation_management.persistence.entity.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findAll();
    Tag save(Tag tag);
    void delete(Long id);
}
