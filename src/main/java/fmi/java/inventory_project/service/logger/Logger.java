package fmi.java.inventory_project.service.logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public abstract class Logger {
    @Value("${logger.level}")
    protected LoggerLevel minLoggerLevel;

    public abstract void info(Object toLog);
    public abstract void debug(Object toLog);
    public abstract void trace(Object toLog);
    public abstract void error(Object toLog);
}
