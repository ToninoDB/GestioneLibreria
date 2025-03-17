package com.example.gestionelibreria.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titolo;

    @NotNull
    private Integer annoPubblicazione;

    @ManyToOne
    @JoinColumn(name = "autore_id", nullable = false)
    private Autore autore;
}
