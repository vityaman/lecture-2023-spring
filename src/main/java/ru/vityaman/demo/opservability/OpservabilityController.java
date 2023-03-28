package ru.vityaman.demo.opservability;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ops")
class OpservabilityController {

    @GetMapping("/ping")
    String ping() { return "pong"; }
}
