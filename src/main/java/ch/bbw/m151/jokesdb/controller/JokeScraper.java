package ch.bbw.m151.jokesdb.controller;

import ch.bbw.m151.jokesdb.datamodel.Joke;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class JokeScraper {

    @EventListener(ApplicationReadyEvent.class)
    public void onStartup() {

    }
}
