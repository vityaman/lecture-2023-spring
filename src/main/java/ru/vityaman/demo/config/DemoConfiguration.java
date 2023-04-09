package ru.vityaman.demo.config;

import java.time.Clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DemoConfiguration {
    
    @Bean
    Clock clock() {
        return Clock.systemDefaultZone();
    }

}
