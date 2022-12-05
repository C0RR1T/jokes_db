package ch.bbw.m151.jokesdb.datamodel;

import lombok.AllArgsConstructor;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ParamBuilder {
    private String basePath;
    private String categories;
    private String blackList;

    private String lang;

    private List<String> allowedLang =
            List.of("cs", "en", "de", "es", "fr", "pt");

    public ParamBuilder(String basePath, String categories, String blackList, String lang) {
        this.basePath = basePath;
        this.categories = categories;
        this.blackList = blackList;
        this.lang = lang;
    }

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

        } else {
            builder.path("Any");
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
        if (lang != null && allowedLang.contains(lang))
            builder.queryParam("lang", lang);


        return builder.build();
    }
}
