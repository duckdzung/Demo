import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DateRangeValidator implements ConstraintValidator<ValidDateRange, CustomerSearchDto> {

    private String messageKey;
    private final MessageSource messageSource;

    public DateRangeValidator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void initialize(ValidDateRange constraintAnnotation) {
        this.messageKey = constraintAnnotation.messageKey();
    }

    private String getMessage() {
        return messageSource.getMessage(messageKey, null, LocaleContextHolder.getLocale());
    }

    @Override
    public boolean isValid(CustomerSearchDto dto, ConstraintValidatorContext context) {
        if (dto.getBirthDateFrom() == null || dto.getBirthDateTo() == null) {
            return true;
        }
        if (dto.getBirthDateTo().isBefore(dto.getBirthDateFrom())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(getMessage()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
