package ru.vityaman.demo.monitoring;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ru.vityaman.demo.api.MonitoringApiDelegate;

@Service
public class MonitoringApi implements MonitoringApiDelegate {
    @Override
    public ResponseEntity<String> opsPingGet() throws Exception {
        return ResponseEntity.ok("pong");
    }
}
