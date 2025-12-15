package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.MovieRequest;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<Movie> findAll() {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public Movie findById(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @PostMapping
    public Movie save(@RequestBody MovieRequest movieRequest) {
        List<Actor> actors = movieRequest.getActors();
        Movie movie = movieRequest.getMovie();
        for (Actor actor : actors) {
            movie.addActor(actor);
        }
        return movieService.save(movie);
    }

    @PutMapping("/{id}")
    public Movie update(@RequestBody Movie movie, @PathVariable Long id) {
        Movie foundMovie = movieService.findById(id);
        movie.setActors(foundMovie.getActors());
        movie.setId(id);
        return movieService.save(movie);
    }

    @DeleteMapping("/{id}")
    public Movie delete(@PathVariable Long id) {
        Movie movie = movieService.findById(id);
        movieService.delete(movie);
        return movie;
    }
}
