package ru.vityaman.demo.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

import ru.vityaman.demo.api.model.GeneralErrorView;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<GeneralErrorView> handleException(
            ResponseStatusException e) throws IOException {
        return new ResponseEntity<>(new GeneralErrorView()
                .code(e.getRawStatusCode())
                .status(e.getStatus().toString())
                .message(e.getReason()),
                HttpStatus.valueOf(e.getRawStatusCode()));
    }
}
