package ch.bbw.m151.jokesdb.datamodel;

import lombok.AllArgsConstructor;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ParamBuilder {
    private String basePath;
    private String categories;
    private String blackList;

    public URI build(UriBuilder builder) {
        if (categories != null) {
            builder.pathSegment(basePath,
                    Arrays.stream(categories.split(","))
                            .map(String::strip)
                            .map(Category::fromString)
                            .filter(Optional::isPresent)
                            .map(Optional::get).distinct()
                            .map(Category::toString)
                            .collect(Collectors.joining(",")));

        }
        if (blackList != null) {
            builder.queryParam("blacklistFlags",
                    Arrays.stream(blackList.split(","))
                            .map(String::strip)
                            .map(Flag::fromString)
                            .filter(Optional::isPresent)
                            .map(Optional::get).distinct()
                            .map(Flag::toString)
                            .collect(Collectors.joining(",")));

        }


        return builder.build();
    }
}
