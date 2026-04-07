package com.universidad.patrones.biblioteca_api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

// DTO de entrada — datos que envía el cliente
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibroRequestDTO {
    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotBlank(message = "El autor es obligatorio")
    private String autor;

    @NotBlank(message = "El ISBN es obligatorio")
    private String isbn;

    @Min(value = 1800, message = "El año debe ser válido")
    @Max(value = 2100, message = "El año no puede ser mayor a 2100")
    private Integer anioPublicacion;

    private String categoria;
}
