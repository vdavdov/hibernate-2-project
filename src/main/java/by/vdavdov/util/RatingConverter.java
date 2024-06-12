package by.vdavdov.util;

import by.vdavdov.constants.RatingEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<RatingEnum, String> {

    @Override
    public String convertToDatabaseColumn(RatingEnum attribute) {
        return attribute.getValue();
    }

    @Override
    public RatingEnum convertToEntityAttribute(String dbData) {
        RatingEnum[] values = RatingEnum.values();
        for (RatingEnum ratingEnum : values) {
            if (ratingEnum.getValue().equals(dbData)) {
                return ratingEnum;
            }
        }
        return null;
    }
}
