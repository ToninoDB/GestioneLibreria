package com.example.gestionelibreria.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO per Libro")
public class LibroDTO {

    @Schema(description = "ID del libro", example = "1")
    private Long id;

    @NotBlank(message = "Il titolo è obbligatorio")
    @Size(min = 3, max = 100, message = "Il titolo deve avere tra 3 e 100 caratteri")
    @Schema(description = "Titolo del libro", example = "Spring Boot Masterclass")
    private String titolo;

    @Positive(message = "L'anno di pubblicazione deve essere positivo")
    @Schema(description = "Anno di pubblicazione", example = "2024")
    private Integer annoPubblicazione;

    @NotNull(message = "L'autore è obbligatorio")
    @Schema(description = "ID dell'autore collegato", example = "1")
    private Long autoreId;
}
