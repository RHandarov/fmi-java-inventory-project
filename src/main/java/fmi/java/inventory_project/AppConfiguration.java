package fmi.java.inventory_project;

import fmi.java.inventory_project.service.logger.ConsoleLogger;
import fmi.java.inventory_project.service.logger.FileLogger;
import fmi.java.inventory_project.service.logger.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Value("${logger.type}")
    private String loggerType;

    @Bean
    public Logger logger() {
        if ("console".equals(loggerType)) {
            return new ConsoleLogger();
        } else if ("file".equals(loggerType)) {
            return new FileLogger();
        }

        throw new RuntimeException("Logger type is not valid!");
    }
}
