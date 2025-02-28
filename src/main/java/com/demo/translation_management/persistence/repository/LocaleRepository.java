package com.demo.translation_management.persistence.repository;

import com.demo.translation_management.persistence.entity.Locale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocaleRepository extends JpaRepository<Locale, Long> {
}
