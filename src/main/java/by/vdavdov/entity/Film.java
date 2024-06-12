package by.vdavdov.entity;


import by.vdavdov.constants.FeaturesEnum;
import by.vdavdov.constants.RatingEnum;
import by.vdavdov.util.RatingConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Year;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "film", schema = "movie")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Short id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "release_year", columnDefinition = "year")
    private Year year;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @Column(name = "rental_duration", nullable = false)
    private Byte rentalDuration;

    @Column(name = "rental_rate", nullable = false)
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;

    @Column(name = "replacement_cost", nullable = false)
    private BigDecimal replacementCost;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update")
    private Date updated;

    @Column(name = "rating", columnDefinition = "enum ('G', 'PG', 'PG-13', 'R', 'NC-17') default 'G'")
    @Convert(converter = RatingConverter.class)
    private RatingEnum rating;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Column(name = "special_features", columnDefinition = "set ('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    private String specialFeatures;

    @ManyToMany
    @JoinTable(name = "film_actor", joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"), inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"))
    private Set<Actor> actors;

    @ManyToMany
    @JoinTable(name = "film_category", joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    private Set<Category> categories;

    public void setSpecialFeatures(Set<FeaturesEnum> specialFeatures) {
        if (Objects.isNull(specialFeatures)) {
            this.specialFeatures = null;
        } else {
            this.specialFeatures = specialFeatures.stream().map(FeaturesEnum::getValue).collect(Collectors.joining(","));
        }

    }

    public Set<FeaturesEnum> getSpecialFeatures() {
        if (Objects.isNull(specialFeatures) || specialFeatures.isEmpty()) {
            return null;
        }

        Set<FeaturesEnum> result = new HashSet<>();
        String[] split = specialFeatures.split(",");

        for (String feature : split) {
            result.add(FeaturesEnum.fromValue(feature));
        }
        result.remove(null);
        return result;
    }
}
