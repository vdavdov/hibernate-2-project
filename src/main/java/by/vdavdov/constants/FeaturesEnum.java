package by.vdavdov.constants;


public enum FeaturesEnum {
    Trailers("Trailers"),
    Commentaries("Commentaries"),
    Deleted_Scenes("Deleted Scenes"),
    Behind_the_Scenes("Behind the Scenes");


    private final String value;

    FeaturesEnum() {
        this.value = this.name();
    }

    FeaturesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FeaturesEnum fromValue(final String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        for (final FeaturesEnum feature : FeaturesEnum.values()) {
            if (feature.getValue().equalsIgnoreCase(value)) {
                return feature;
            }
        }
        return null;
    }
}
