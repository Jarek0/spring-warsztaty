package pl.edu.pollub.warsztaty.common.exception;

import java.util.Map;

public class ObjectNotFoundException extends ParametrizedException {

    public ObjectNotFoundException(String code, String message, Map<String, String> params) {
        super(code, message, params);
    }
}
