package com.example.gestionelibreria.DTO;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
@Schema(description = "DTO per Autore")
public class AutoreDTO {

    @Schema(description = "ID dell'autore", example = "1")
    private Long id;

    @NotBlank(message = "Il nome è obbligatorio")
    @Schema(description = "Nome dell'autore", example = "Mario")
    private String nome;

    @NotBlank(message = "Il cognome è obbligatorio")
    @Schema(description = "Cognome dell'autore", example = "Rossi")
    private String cognome;

    @Schema(description = "Lista degli ID dei libri associati", example = "[1, 2, 3]")
    private List<Long> libriId;
}
