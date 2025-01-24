package com.github.pieter_groenendijk.datasource.repositories.genre;

import com.github.pieter_groenendijk.domain.entities.product.Genre;

import java.util.List;
import java.util.Optional;

public interface IGenreRepository {

    Optional<Genre> retrieveGenreById(long id);
    List<Genre> retrieveGenreList();
    void store(Genre genre);
    void update(Genre genre);
}