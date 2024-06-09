package by.vdavdov.constants;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum RatingEnum {
    G,
    PG,
    PG_13("PG-13"),
    R,
    NC_17("NC-17");

    private final String value;

    RatingEnum() {
        this.value = this.name();
    }

    RatingEnum(String value) {
        this.value = value;
    }
}
