package ru.vityaman.demo.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleException(
            ResponseStatusException e
    ) throws IOException {
        return new ResponseEntity<>(
                Map.of(
                        "code", e.getRawStatusCode(),
                        "status", e.getStatus(),
                        "message", e.getMessage()
                ),
                HttpStatus.valueOf(e.getRawStatusCode())
        );
    }
}
