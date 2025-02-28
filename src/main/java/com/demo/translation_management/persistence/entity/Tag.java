package com.demo.translation_management.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
