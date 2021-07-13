package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

    private MovieService service;

    @Autowired
    public MovieResource(MovieService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<MovieDTO>> findAll(@RequestParam(value = "genreId", defaultValue = "0") Long genreId, Pageable pageable){
        Page<MovieDTO> page = service.findAll(genreId, pageable);
        return ResponseEntity.ok(page);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable("id") Long id){
        MovieDTO movieDTO = service.findById(id);
        return ResponseEntity.ok(movieDTO);
    }
}
