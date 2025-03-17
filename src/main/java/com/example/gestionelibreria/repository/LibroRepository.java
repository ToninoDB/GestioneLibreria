package com.example.gestionelibreria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestionelibreria.models.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTitolo(String titolo);
}
