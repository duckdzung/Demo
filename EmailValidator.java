import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private String messageKey;
    private final MessageSource messageSource;

    public EmailValidator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        this.messageKey = constraintAnnotation.messageKey();
    }

    private String getMessage() {
        return messageSource.getMessage(messageKey, null, LocaleContextHolder.getLocale());
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || !Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(getMessage()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
