package ch.bbw.m151.jokesdb.datamodel;

import java.util.Optional;

public enum Category {
    PROGRAMMING("Programming"),
    MISC("Misc"),
    DARK("Dark"),
    PUN("Pun"),
    SPOOKY("Spooky"),
    CHRISTMAS("Christmas");

    public static Optional<Category> fromString(String category) {
        return switch (category) {
            case "Programming" -> Optional.of(PROGRAMMING);
            case "Misc" -> Optional.of(MISC);
            case "Dark" -> Optional.of(DARK);
            case "Pun" -> Optional.of(PUN);
            case "Spooky" -> Optional.of(SPOOKY);
            case "Christmas" -> Optional.of(CHRISTMAS);
            default -> Optional.empty();
        };
    }

    private final String category;

    Category(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return this.category;
    }
}
