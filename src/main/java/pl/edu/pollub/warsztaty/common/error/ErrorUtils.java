package pl.edu.pollub.warsztaty.common.error;

import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ErrorUtils {
    public static void rejectValue(Errors e, String code) {
        e.rejectValue("", "", code);
    }
}
