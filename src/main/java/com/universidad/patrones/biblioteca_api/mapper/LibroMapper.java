package com.universidad.patrones.biblioteca_api.mapper;

import com.universidad.patrones.biblioteca_api.dto.*;
import com.universidad.patrones.biblioteca_api.model.Libro;
import org.springframework.stereotype.Component;

@Component
public class LibroMapper {
    public LibroResponseDTO toResponse(Libro libro) {
        return new LibroResponseDTO(
            libro.getId(),
            libro.getTitulo(),
            libro.getAutor(),
            libro.getIsbn(),
            libro.getAnioPublicacion(),
            libro.getCategoria()
        );
    }

    public Libro toEntity(LibroRequestDTO dto) {
        return new Libro(
            null,
            dto.getTitulo(),
            dto.getAutor(),
            dto.getIsbn(),
            dto.getAnioPublicacion(),
            dto.getCategoria()
        );
    }
}
