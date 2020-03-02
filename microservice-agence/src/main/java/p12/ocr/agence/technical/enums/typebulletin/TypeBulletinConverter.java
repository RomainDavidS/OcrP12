package p12.ocr.agence.technical.enums.typebulletin;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TypeBulletinConverter implements AttributeConverter<TypeBulletin,String> {

    @Override
    public String convertToDatabaseColumn(TypeBulletin typeBulletin) {
        return typeBulletin != null ? typeBulletin.getCode() : null;
    }

    @Override
    public TypeBulletin convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(TypeBulletin.values())
                .filter(c -> c.getCode().equals( code ))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
