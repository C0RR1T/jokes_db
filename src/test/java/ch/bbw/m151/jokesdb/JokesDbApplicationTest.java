package ch.bbw.m151.jokesdb;

import ch.bbw.m151.jokesdb.datamodel.Joke;
import ch.bbw.m151.jokesdb.repository.JokesRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class JokesDbApplicationTest implements WithAssertions {

    @Autowired
    JokesRepository jokesRepository;

    @Autowired
    private WebTestClient client;

    @BeforeEach
    public void cleanup() {
        jokesRepository.deleteAll();
    }


    @Test
    public void jokeDoesExist() {
        client.get().uri("/jokes").exchange();
        assertThat(jokesRepository.count()).isGreaterThan(0);
        assertThat(jokesRepository.findAnyJoke()).isNotEmpty();
    }

    @Test
    public void jokeById() {
        var joke =
                client.get().uri("/jokes/5").exchange()
                        .expectBody(Joke.class).returnResult();
        assertThat(joke.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(joke.getResponseBody().getId()).isEqualTo(5);
        assertThat(jokesRepository.count()).isGreaterThan(0);
    }
}
