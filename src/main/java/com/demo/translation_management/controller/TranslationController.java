package com.demo.translation_management.controller;

import com.demo.translation_management.base.dto.request.TranslationRequestDTO;
import com.demo.translation_management.base.dto.response.TranslationResponseDTO;
import com.demo.translation_management.persistence.entity.Translation;
import com.demo.translation_management.service.TranslationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/translation")
class TranslationController {
    @Autowired
    private TranslationService translationService;

    @GetMapping
    public List<TranslationResponseDTO> getAll() throws Exception {
        return translationService.findAll();
    }

    @GetMapping("/{id}")
    public TranslationResponseDTO getById(@PathVariable Long id) throws Exception {
        return translationService.findById(id);
    }

    @PostMapping
    public TranslationResponseDTO create(@RequestBody TranslationRequestDTO translation) throws Exception {
        return translationService.save(translation);
    }

    @PutMapping("/{id}")
    public TranslationResponseDTO update(@PathVariable Long id, @RequestBody TranslationRequestDTO updatedTranslation) throws Exception {
        return translationService.update(id, updatedTranslation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        translationService.delete(id);
    }

    @GetMapping("/search")
    public List<TranslationResponseDTO> search(@RequestParam Long localeId, @RequestParam Long tagId, @RequestParam String key) throws Exception {
        return translationService.search(localeId, tagId, key);
    }

    @Operation(summary = "Add number to generate translation data to the database")
    @GetMapping("generate/{number}")
    public ResponseEntity<?> generate(@PathVariable int number) throws Exception {
        translationService.generate(number);
        return ResponseEntity.ok("Generated " + number + " translations");
    }
}
