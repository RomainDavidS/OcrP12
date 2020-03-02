package p12.ocr.web.technical.fieldmatch;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Création d'une annotation personalisé afin de vérifier la correspondance de 2 champs.
 */
@Target({
        TYPE,
        ANNOTATION_TYPE
})
@Retention(RUNTIME)
@Constraint(validatedBy = FieldNoMatchValidator.class)
@Documented
public @interface FieldNoMatch {
    String message() default "{constraints.field-match}";
    Class < ? > [] groups() default {};
    Class < ? extends Payload > [] payload() default {};
    String first();
    String second();

    @Target({
            TYPE,
            ANNOTATION_TYPE
    })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        FieldNoMatch[] value();
    }
}
