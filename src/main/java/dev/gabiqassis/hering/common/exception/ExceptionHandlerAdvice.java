package dev.gabiqassis.hering.common.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

import static dev.gabiqassis.hering.common.constants.ExceptionHandlerAdviceConstants.STACKTRACE_PROPERTY;
import static dev.gabiqassis.hering.common.constants.ExceptionHandlerAdviceConstants.TIMESTAMP_PROPERTY;
import static java.lang.String.format;
import static java.time.LocalTime.now;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ProblemDetail problemDetail = forStatusAndDetail(BAD_REQUEST, "Validation failed for argument");
        List<String> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> format("%s: %s", error.getField(), error.getDefaultMessage()))
                .toList();
        problemDetail.setProperty(TIMESTAMP_PROPERTY, Instant.now());
        problemDetail.setProperty(STACKTRACE_PROPERTY, errors);
        return problemDetail;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ProblemDetail handleIllegalArgumentException(IllegalArgumentException exception) {
        return exceptionToProblemDetailForStatusAndDetail(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    private ProblemDetail handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        return exceptionToProblemDetailForStatusAndDetail(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ProblemDetail handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return exceptionToProblemDetailForStatusAndDetail(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    private ProblemDetail handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        return exceptionToProblemDetailForStatusAndDetail(CONFLICT, exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    private ProblemDetail handleConstraintViolationException(ConstraintViolationException exception) {
        return exceptionToProblemDetailForStatusAndDetail(CONFLICT, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ProblemDetail handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        return exceptionToProblemDetailForStatusAndDetail(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(ResponseStatusException.class)
    private ProblemDetail handleResponseStatusException(ResponseStatusException exception) {
        return exceptionToProblemDetailForStatusAndDetail(exception.getStatusCode(), exception.getReason());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ProblemDetail handleEntityNotFoundException(EntityNotFoundException exception) {
        return exceptionToProblemDetailForStatusAndDetail(NOT_FOUND, exception.getMessage());
    }

    private ProblemDetail exceptionToProblemDetailForStatusAndDetail(HttpStatusCode status, String detail) {
        ProblemDetail problemDetail = forStatusAndDetail(status, detail);
        problemDetail.setProperty(TIMESTAMP_PROPERTY, now());
        return problemDetail;
    }
}
