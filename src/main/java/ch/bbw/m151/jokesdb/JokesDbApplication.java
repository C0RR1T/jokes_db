package ch.bbw.m151.jokesdb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JokesDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(JokesDbApplication.class, args);
    }

}
