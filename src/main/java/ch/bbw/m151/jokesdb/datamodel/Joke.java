package ch.bbw.m151.jokesdb.datamodel;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Entity
@Data
public class Joke {
    @Id
    private int id;
    private boolean error;
    private String categories;
    private String type;
    private String setup;
    private String delivery;
    @Column(length = 500)
    private String joke;
    @Embedded
    private Flags flags;
    private boolean safe;
    private String lang;


    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDate;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateDate;

    public List<Category> getCategories() {
        return this.categories != null ?
                Arrays.stream(this.categories.split(","))
                        .map(Category::fromString).filter(Optional::isPresent)
                        .map(Optional::get).toList() :
                new ArrayList<>();
    }

    public void setCategories(List<Category> categories) {
        this.categories =
                categories.stream().map(Category::toString)
                        .collect(Collectors.joining(","));
    }


}
