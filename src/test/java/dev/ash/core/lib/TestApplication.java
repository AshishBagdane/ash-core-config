package dev.ash.core.lib;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = "dev.ash.core.lib")
public class TestApplication {
  // Empty configuration class just to bootstrap Spring Boot test context
}