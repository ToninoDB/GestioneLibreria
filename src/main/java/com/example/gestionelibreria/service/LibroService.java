package com.example.gestionelibreria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.gestionelibreria.DTO.AutoreDTO;
import com.example.gestionelibreria.DTO.LibroDTO;
import com.example.gestionelibreria.exception.ResourceNotFoundException;
import com.example.gestionelibreria.models.Autore;
import com.example.gestionelibreria.models.Libro;
import com.example.gestionelibreria.repository.AutoreRepository;
import com.example.gestionelibreria.repository.LibroRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutoreRepository autoreRepository;

    // Ottieni tutti i libri
    public List<LibroDTO> getAllLibri() {
        return libroRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Ottieni un libro per titolo
    public LibroDTO getByTitolo(String titolo) {
        Libro libro = libroRepository.findByTitolo(titolo)
                .orElseThrow(() -> new ResourceNotFoundException("Libro con titolo '" + titolo + "' non trovato"));

        return convertToDto(libro);
    }

    // Aggiungi un libro
    public LibroDTO addLibro(LibroDTO libroDTO) {
        // Verifica se l'autore esiste
        Autore autore = autoreRepository.findById(libroDTO.getAutoreId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Autore con id '" + libroDTO.getAutoreId() + "' non trovato"));

        Libro libro = convertToEntity(libroDTO);
        libro.setAutore(autore);

        libro = libroRepository.save(libro);

        return convertToDto(libro);
    }

    // Elimina un libro per ID
    public void deleteLibro(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new ResourceNotFoundException("Libro con id '" + id + "' non trovato");
        }
        libroRepository.deleteById(id);
    }

    // Aggiorna un libro preso per ID
    public LibroDTO updateLibro(Long id, LibroDTO libroDTO) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro con id '" + id + "' non trovato"));

        Autore autore = autoreRepository.findById(libroDTO.getAutoreId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Autore con id '" + libroDTO.getAutoreId() + "' non trovato"));

        libro.setTitolo(libroDTO.getTitolo());
        libro.setAnnoPubblicazione(libroDTO.getAnnoPubblicazione());
        libro.setAutore(autore);

        libro = libroRepository.save(libro);

        return convertToDto(libro);
    }

    // Aggiungi un nuovo autore
    public AutoreDTO addAutore(AutoreDTO autoreDTO) {
        Autore autore = new Autore();
        autore.setNome(autoreDTO.getNome());
        autore.setCognome(autoreDTO.getCognome());

        autore = autoreRepository.save(autore);

        return new AutoreDTO(
                autore.getId(),
                autore.getNome(),
                autore.getCognome(),
                autore.getLibri() != null
                        ? autore.getLibri().stream().map(Libro::getId).collect(Collectors.toList())
                        : List.of() // Ritorna lista vuota se non ci sono libri
        );
    }

    // Conversione da entità a DTO
    private LibroDTO convertToDto(Libro libro) {
        LibroDTO dto = new LibroDTO();
        dto.setId(libro.getId());
        dto.setTitolo(libro.getTitolo());
        dto.setAnnoPubblicazione(libro.getAnnoPubblicazione());
        dto.setAutoreId(libro.getAutore().getId());
        return dto;
    }

    // Conversione da DTO a entità
    private Libro convertToEntity(LibroDTO dto) {
        Libro libro = new Libro();
        libro.setTitolo(dto.getTitolo());
        libro.setAnnoPubblicazione(dto.getAnnoPubblicazione());
        return libro;
    }
}
