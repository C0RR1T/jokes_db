package ch.bbw.m151.jokesdb.controller;

import ch.bbw.m151.jokesdb.datamodel.Joke;
import ch.bbw.m151.jokesdb.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jokes")
public class JokeController {
    private final JokeService service;


    @Autowired
    public JokeController(JokeService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Joke> getJoke(@PathVariable Integer id) {
        return service.getJoke(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("")
    public ResponseEntity<Joke> getRandomJoke(@RequestParam("categories") String categories, @RequestParam("blacklist") String blacklist, @RequestParam("lang") String lang) {
        return service.getRandomJoke().map(ResponseEntity::ok)
                .orElse(ResponseEntity.internalServerError().build());
    }

    @GetMapping("/jotd")
    public ResponseEntity<Joke> getJokeOfTheDay() {
        return service.getJokeOfTheDay().map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
