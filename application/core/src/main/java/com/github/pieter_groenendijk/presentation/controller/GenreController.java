package com.github.pieter_groenendijk.presentation.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import com.github.pieter_groenendijk.service.IProductService;
import com.github.pieter_groenendijk.entity.product.Genre;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
@RequestMapping("/genre")
public class GenreController {
    private final IProductService PRODUCT_SERVICE;

    private GenreController(
        IProductService productService
    ) {
        this.PRODUCT_SERVICE = productService;
    }

    @Operation(summary = "Retrieve a genre", description = "Retrieve a genre by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genre found"),
            @ApiResponse(responseCode = "404", description = "Genre not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveGenreById(@PathVariable("id") long id) {
        Genre genre = PRODUCT_SERVICE.retrieveGenreById(id);
        return ResponseEntity.ok(genre);
    }

    @Operation(summary = "Create a genre", description = "Add a new genre to the database")
    @PostMapping
    public ResponseEntity<?> createGenre(@RequestBody Genre genre) {
        PRODUCT_SERVICE.store(genre);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update a genre", description = "Change a genre ")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateGenre(@PathVariable("id") long id, @RequestBody Genre genre) {
        PRODUCT_SERVICE.update(id, genre);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Retrieve a list of genres", description = "Retrieve a list of genres")
    @GetMapping("/getAll")
    public ResponseEntity<List<Genre>> retrieveGenreList() {
        List<Genre> genres = PRODUCT_SERVICE.retrieveGenreList();
        if (genres.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(genres);
        }
    }

}