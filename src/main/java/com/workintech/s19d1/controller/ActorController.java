package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actor")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @GetMapping
    public List<Actor> findAll() {
        return actorService.findAll();
    }

    @GetMapping("/{id}")
    public Actor findById(@PathVariable Long id) {
        return actorService.findById(id);
    }

    @PostMapping
    public Actor save(@RequestBody ActorRequest actorRequest) {
        List<Movie> movies = actorRequest.getMovies();
        Actor actor = actorRequest.getActor();
        for (Movie movie : movies) {
            actor.addMovie(movie);
        }
        return actorService.save(actor);
    }

    @PutMapping("/{id}")
    public Actor update(@RequestBody Actor actor, @PathVariable Long id) {
        Actor foundActor = actorService.findById(id);
        actor.setMovies(foundActor.getMovies());
        actor.setId(id);
        return actorService.save(actor);
    }

    @DeleteMapping("/{id}")
    public Actor delete(@PathVariable Long id) {
        Actor actor = actorService.findById(id);
        actorService.delete(actor);
        return actor;
    }
}
