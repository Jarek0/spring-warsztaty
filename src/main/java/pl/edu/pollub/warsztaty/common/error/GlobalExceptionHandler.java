package pl.edu.pollub.warsztaty.common.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.edu.pollub.warsztaty.common.exception.ObjectNotFoundException;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.*;
import static pl.edu.pollub.warsztaty.common.error.Errors.*;
import static pl.edu.pollub.warsztaty.common.error.Errors.UNAUTHORIZED;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    )
    {
        BindingResult bindingResult = ex.getBindingResult();

        List<ErrorDto> fieldErrors = collectErrors(bindingResult);

        List<GlobalErrorDto> globalErrors = collectGlobalErrors(bindingResult);

        ErrorsDto apiErrorsView = new ErrorsDto(fieldErrors, globalErrors);

        return new ResponseEntity<>(apiErrorsView, UNPROCESSABLE_ENTITY);
    }

    private List<ErrorDto> collectErrors(BindingResult bindingResult) {
        return bindingResult
                .getFieldErrors()
                .stream()
                .map(e -> new ErrorDto(e.getField(), e.getDefaultMessage()))
                .collect(toList());
    }

    private List<GlobalErrorDto> collectGlobalErrors(BindingResult bindingResult) {
        return bindingResult
                .getGlobalErrors()
                .stream()
                .map(e -> new GlobalErrorDto(e.getDefaultMessage()))
                .collect(toList());
    }

    @ExceptionHandler(value = {ObjectNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ResponseEntity<String> handleNotFoundException(Exception ex, WebRequest req) {
        System.out.println("exceptions: "+ex.getClass().getSimpleName()+" message: " + ex.getMessage());
        ex.printStackTrace();

        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(value = {BadCredentialsException.class, AccessDeniedException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<String> handleUnauthorized(Exception ex, WebRequest req) {
        log.error("exceptions: "+ex.getClass().getSimpleName()+" message: " + ex.getMessage());
        ex.printStackTrace();

        return new ResponseEntity<>( UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<String> handleInternalServerError(Exception ex, WebRequest req) {
        log.error("exceptions: "+ex.getClass().getSimpleName()+" message: " + ex.getMessage());
        ex.printStackTrace();

        return new ResponseEntity<>( FATAL_ERROR, INTERNAL_SERVER_ERROR);
    }

}
