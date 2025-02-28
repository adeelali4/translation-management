package com.demo.translation_management.service.Impl;

import com.demo.translation_management.persistence.entity.Tag;
import com.demo.translation_management.persistence.repository.TagRepository;
import com.demo.translation_management.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    public void delete(Long id) {
        tagRepository.deleteById(id);
    }
}
