package ch.bbw.m151.jokesdb.service;

import ch.bbw.m151.jokesdb.datamodel.Joke;
import ch.bbw.m151.jokesdb.datamodel.ParamBuilder;
import ch.bbw.m151.jokesdb.repository.JokesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class JokeService {
    private final JokesRepository repository;
    private final WebClient client;

    private Optional<Joke> jokeOfTheDay;

    @Autowired
    public JokeService(JokesRepository repository) {
        this.repository = repository;
        this.client = WebClient.create("https://v2.jokeapi.dev/joke/");
        this.jokeOfTheDay = getRandomJoke();
    }

    public Optional<Joke> getJoke(Integer id) {
        var joke = repository.findById(id);
        if (joke.isPresent()) {
            return joke;
        }

        return saveRemoteJoke(id);
    }

    public Optional<Joke> getRandomJoke(String categories, String blacklist,
                                        String lang) {
        return saveRemoteJoke(categories, blacklist, lang);
    }

    public Optional<Joke> getRandomJoke() {
        return saveRemoteJoke();
    }

    public Optional<Joke> getJokeOfTheDay() {
        return jokeOfTheDay;
    }

    private Optional<Joke> saveRemoteJoke(Integer id) {
        var joke = getRemoteJoke(id);
        joke.ifPresent(repository::save);
        return joke;
    }

    private Optional<Joke> saveRemoteJoke(String categories, String blacklist,
                                          String lang) {
        var joke = getRandomRemoteJoke(categories, blacklist,
                lang);
        joke.ifPresent(repository::save);
        return joke;
    }

    private Optional<Joke> saveRemoteJoke() {
        return saveRemoteJoke(null, null, null);
    }

    private Optional<Joke> getRemoteJoke(Integer id) {
        return client
                .get().uri((queryBuilder) -> queryBuilder.path("Any")
                        .queryParam("idRange", id).build())
                .retrieve()
                .bodyToMono(Joke.class)
                .onErrorResume(WebClientResponseException.NotFound.class, notFound -> Mono.empty())
                .blockOptional();
    }

    private Optional<Joke> getRandomRemoteJoke(String categories, String blacklist,
                                               String lang) {
        return client.get()
                .uri((queryBuilder) -> new ParamBuilder("", categories,
                        blacklist, lang).build(queryBuilder))
                .retrieve()
                .bodyToMono(Joke.class)
                .onErrorResume(error -> Mono.empty())
                .blockOptional();
    }


    @Scheduled(cron = "0 0 0 * * ?")
    private void createJokeOfTheDay() {
        this.jokeOfTheDay = getRandomJoke();
    }
}
