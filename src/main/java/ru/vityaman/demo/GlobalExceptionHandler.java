package ru.vityaman.demo;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequestException(
        ResponseStatusException e
    ) {
        return new ResponseEntity<>(
            Map.of(
                "code", e.getStatusCode().value(),
                "status", e.getStatusCode(),
                "message", e.getReason()
            ),
            e.getStatusCode()
        );
    }
}
