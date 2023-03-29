package ru.vityaman.demo.monitoring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ops")
class MonitoringController {

    @GetMapping("/ping")
    String ping() { return "pong"; }
}
