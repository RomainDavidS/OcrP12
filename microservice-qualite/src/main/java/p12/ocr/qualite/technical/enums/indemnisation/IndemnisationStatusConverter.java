package p12.ocr.qualite.technical.enums.indemnisation;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class IndemnisationStatusConverter implements AttributeConverter<IndemnisationStatus,String> {

    @Override
    public String convertToDatabaseColumn(IndemnisationStatus indemnisationStatus) {
        return indemnisationStatus != null ? indemnisationStatus.getCode() : null;
    }

    @Override
    public IndemnisationStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(IndemnisationStatus.values())
                .filter(c -> c.getCode().equals( code ))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
