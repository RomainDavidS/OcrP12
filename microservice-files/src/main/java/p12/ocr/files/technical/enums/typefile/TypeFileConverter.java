package p12.ocr.files.technical.enums.typefile;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TypeFileConverter implements AttributeConverter<TypeFile,String> {

    @Override
    public String convertToDatabaseColumn(TypeFile typeFile) {
        return typeFile != null ? typeFile.getCode() : null;
    }

    @Override
    public TypeFile convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(TypeFile.values())
                .filter(c -> c.getCode().equals( code ))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
