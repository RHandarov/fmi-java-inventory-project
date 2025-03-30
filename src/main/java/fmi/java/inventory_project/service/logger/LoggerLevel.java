package fmi.java.inventory_project.service.logger;

public enum LoggerLevel {
    ERROR(0, "ERROR"),
    INFO(1, "INFO"),
    BEGUG(2, "DEBUG"),
    TRACE(3, "TRACE");

    private final int code;
    private final String label;

    LoggerLevel(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }
}
