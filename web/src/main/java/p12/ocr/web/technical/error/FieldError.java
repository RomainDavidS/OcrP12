package p12.ocr.web.technical.error;

import lombok.Getter;

@Getter
public class FieldError<V> {

    private String name;

    private String message;

    private V value;

    private boolean bool;


    public FieldError(String name, String message, V value, boolean bool) {
        this.name = name;
        this.message = message;
        this.value = value;
        this.bool = bool;
    }


}
