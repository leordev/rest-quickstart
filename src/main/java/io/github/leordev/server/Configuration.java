package io.github.leordev.server;

/**
 * Created by lribeiro on 11/11/16.
 */
public class Configuration {

    private int port = 8080;

    private LoggerConfiguration logger;

    public Configuration() {
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void initialize() {
        logger = new LoggerConfiguration();
    }

    public LoggerConfiguration getLogger() {
        return logger;
    }

    public void setLogger(LoggerConfiguration logger) {
        this.logger = logger;
    }

    public int getPort() {
        return port;
    }
}
