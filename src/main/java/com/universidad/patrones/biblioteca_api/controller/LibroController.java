package com.universidad.patrones.biblioteca_api.controller;

import com.universidad.patrones.biblioteca_api.dto.*;
import com.universidad.patrones.biblioteca_api.mapper.LibroMapper;
import com.universidad.patrones.biblioteca_api.service.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v2/libros")
@Tag(name = "Libros", description = "Operaciones CRUD sobre el catálogo de libros")
public class LibroController {
    private final LibroService service;
    private final LibroMapper mapper;

    public LibroController(LibroService service, LibroMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    @Operation(summary = "Listar todos los libros")
    public List<LibroResponseDTO> listar() {
        return service.findAll().stream().map(mapper::toResponse).toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener libro por ID")
    public ResponseEntity<LibroResponseDTO> obtener(@PathVariable Long id) {
        return service.findById(id).map(mapper::toResponse).map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear nuevo libro")
    public ResponseEntity<LibroResponseDTO> crear(@RequestBody @Valid LibroRequestDTO dto) {
        var guardado = service.save(mapper.toEntity(dto));
        return ResponseEntity.status(201).body(mapper.toResponse(guardado));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar libro por ID")
    public ResponseEntity<LibroResponseDTO> actualizar(@PathVariable Long id, @RequestBody @Valid LibroRequestDTO dto) {
        var actualizado = service.update(id, mapper.toEntity(dto));
        return ResponseEntity.ok(mapper.toResponse(actualizado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar libro por ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar libros por palabra clave")
    public List<LibroResponseDTO> buscar(@RequestParam String q) {
        return service.buscarPorPalabra(q).stream().map(mapper::toResponse).toList();
    }
}