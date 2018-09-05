package pl.edu.pollub.warsztaty.common.exception;

class ExceptionWithCode extends RuntimeException {

    private String code;

    ExceptionWithCode(String code, String message) {
        super(message);
        this.code = code;
    }
}
