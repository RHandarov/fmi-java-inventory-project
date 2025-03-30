package fmi.java.inventory_project.service.logger;

import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ConsoleLogger extends Logger {
    @Override
    public void info(Object toLog) {
        if (LoggerLevel.INFO.getCode() <= minLoggerLevel.getCode()) {
            logInformation(toLog, LoggerLevel.INFO);
        }
    }

    @Override
    public void debug(Object toLog) {
        if (LoggerLevel.BEGUG.getCode() <= minLoggerLevel.getCode()) {
            logInformation(toLog, LoggerLevel.BEGUG);
        }
    }

    @Override
    public void trace(Object toLog) {
        if (LoggerLevel.TRACE.getCode() <= minLoggerLevel.getCode()) {
            logInformation(toLog, LoggerLevel.TRACE);
        }
    }

    @Override
    public void error(Object toLog) {
        if (LoggerLevel.ERROR.getCode() <= minLoggerLevel.getCode()) {
            logInformation(toLog, LoggerLevel.ERROR);
        }
    }

    private void logInformation(Object toLog, LoggerLevel loggerLevel) {
        String logMessage = '[' +
                loggerLevel.getLabel() +
                ' ' +
                Instant.now() +
                "]: " +
                toLog;

        System.out.println(logMessage);
    }
}
