package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Genre;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

public class GenreDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    public GenreDTO() {
    }

    public GenreDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public GenreDTO(Genre genre){
        this(genre.getId(), genre.getName());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenreDTO genreDTO = (GenreDTO) o;

        return Objects.equals(id, genreDTO.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
