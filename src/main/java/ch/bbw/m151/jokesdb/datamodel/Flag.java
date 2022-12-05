package ch.bbw.m151.jokesdb.datamodel;

import java.util.Optional;

public enum Flag {
    NSFW("nsfw"),
    RELIGIOUS("religious"),
    POLITICAL("political"),
    RACIST("racist"),
    SEXIST("sexist"),
    EXPLICIT("explicit");

    private final String flag;

    Flag(String flag) {
        this.flag = flag;
    }

    static Optional<Flag> fromString(String flag) {
        return switch (flag.toLowerCase()) {
            case "nsfw" -> Optional.of(NSFW);
            case "religious" -> Optional.of(RELIGIOUS);
            case "political" -> Optional.of(POLITICAL);
            case "racist" -> Optional.of(RACIST);
            case "sexist" -> Optional.of(SEXIST);
            case "explicit" -> Optional.of(EXPLICIT);
            default -> Optional.empty();
        };
    }

    public String toString() {
        return flag;
    }
}
