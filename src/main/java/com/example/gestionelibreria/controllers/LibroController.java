package com.example.gestionelibreria.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gestionelibreria.DTO.AutoreDTO;
import com.example.gestionelibreria.DTO.LibroDTO;
import com.example.gestionelibreria.service.LibroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Libri", description = "Gestione dei libri")
@RestController
@RequestMapping("/api/libri")
@RequiredArgsConstructor
public class LibroController {
    private final LibroService libroService;

    // Ottieni tutti i libri
    @Operation(summary = "Ottieni tutti i libri", description = "Restituisce una lista di tutti i libri disponibili")
    @ApiResponse(responseCode = "200", description = "Lista restituita con successo")
    @GetMapping
    public ResponseEntity<List<LibroDTO>> getAllLibri() {
        List<LibroDTO> libri = libroService.getAllLibri();
        return ResponseEntity.ok(libri); // HTTP 200 OK
    }

    // Ottieni un libro tramite titolo
    @GetMapping("/{titolo}")
    @Operation(summary = "Ottieni un libro per titolo", description = "Restituisce un libro tramite il titolo specificato")
    @ApiResponse(responseCode = "200", description = "Libro trovato")
    @ApiResponse(responseCode = "404", description = "Libro non trovato")
    public ResponseEntity<LibroDTO> getLibroByTitolo(@PathVariable String titolo) {
        LibroDTO libro = libroService.getByTitolo(titolo);
        return ResponseEntity.ok(libro); // HTTP 200 OK
    }

    // Aggiungi un nuovo libro
    @Operation(summary = "Aggiungi un nuovo libro", description = "Crea un nuovo libro nella libreria")
    @ApiResponse(responseCode = "201", description = "Libro creato con successo")
    @PostMapping
    public ResponseEntity<LibroDTO> createLibro(@Valid @RequestBody LibroDTO libroDTO) {
        LibroDTO savedLibro = libroService.addLibro(libroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLibro); // HTTP 201 Created
    }

    // Aggiungi un nuovo autore
    @Operation(summary = "Aggiungi un nuovo autore", description = "Crea un nuovo autore nella libreria")
    @ApiResponse(responseCode = "201", description = "Autore creato con successo")
    @ApiResponse(responseCode = "400", description = "Dati di input non validi")
    @PostMapping("/autori")
    public ResponseEntity<AutoreDTO> createAutore(@Valid @RequestBody AutoreDTO autoreDTO) {
        AutoreDTO savedAutore = libroService.addAutore(autoreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAutore); // HTTP 201 Created
    }

    // Elimina un libro tramite ID
    @Operation(summary = "Elimina un libro", description = "Elimina un libro tramite ID")
    @ApiResponse(responseCode = "204", description = "Libro eliminato con successo")
    @ApiResponse(responseCode = "404", description = "Libro non trovato")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        libroService.deleteLibro(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }

    // Aggiorna un libro tramite ID
    @Operation(summary = "Aggiorna un libro", description = "Aggiorna completamente un libro esistente tramite ID")
    @ApiResponse(responseCode = "200", description = "Libro aggiornato con successo")
    @ApiResponse(responseCode = "400", description = "Richiesta non valida o dati non validi")
    @ApiResponse(responseCode = "404", description = "Libro non trovato")
    @PutMapping("/{id}")
    public ResponseEntity<LibroDTO> updateLibro(
            @PathVariable Long id,
            @Valid @RequestBody LibroDTO libroDTO) {

        LibroDTO updatedLibro = libroService.updateLibro(id, libroDTO);
        return ResponseEntity.ok(updatedLibro); // HTTP 200 OK
    }

}
