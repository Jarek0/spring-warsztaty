package pl.edu.pollub.warsztaty.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class ParametrizedException extends ExceptionWithCode {

    private Map<String, String> params;

    public ParametrizedException(String code, String message, Map<String, String> params) {
        super(code, message);
        this.params = params;
    }
}
