package ru.vityaman.demo.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import ru.vityaman.demo.api.model.GeneralErrorView;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<GeneralErrorView> handleException(
            ResponseStatusException e) {
        return new ResponseEntity<>(new GeneralErrorView()
                .code(e.getRawStatusCode())
                .status(e.getStatus().toString())
                .message(e.getReason()),
                HttpStatus.valueOf(e.getRawStatusCode()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GeneralErrorView> handleConstraintViolation(
            ConstraintViolationException e) {
        return new ResponseEntity<>(new GeneralErrorView()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.toString())
                .message(e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
