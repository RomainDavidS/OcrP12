package p12.ocr.web.technical.fieldmatch;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Classe de l'annotation personalisé de vérification de correspondance de 2 champs.
 */
public class FieldNoMatchValidator implements ConstraintValidator <FieldNoMatch, Object > {

    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final FieldNoMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value, secondFieldName);
            return secondObj != null || firstObj != null && !firstObj.equals(secondObj);
        } catch (final Exception ignore) {}
        return true;
    }
}
