package com.ashishbagdane.lib.core;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.ashishbagdane.lib.core")
public class TestApplication {
    // Empty configuration class just to bootstrap Spring Boot test context
}
