package ch.bbw.m151.jokesdb.repository;

import ch.bbw.m151.jokesdb.datamodel.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JokesRepository extends JpaRepository<Joke, Integer> {

    @Query("SELECT j from Joke j ORDER BY random()")
    Optional<Joke> findAnyJoke();
}
