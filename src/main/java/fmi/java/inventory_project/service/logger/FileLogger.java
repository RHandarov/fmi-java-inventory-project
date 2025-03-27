package fmi.java.inventory_project.service.logger;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;

@Service
public class FileLogger extends Logger {
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

        File log = new File("log.txt");
        try (PrintWriter out = new PrintWriter(new FileWriter(log, true))) {
            out.println(logMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
