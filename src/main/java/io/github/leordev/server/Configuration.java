package io.github.leordev.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by lribeiro on 11/11/16.
 */
public class Configuration {

    private static Configuration configuration;

    private int port = 8080;

    private LoggerConfiguration logger;

    public static Configuration getInstance() {
        if(configuration == null) {
            configuration = new Configuration();
        }
        return configuration;
    }

    private Configuration() {
        System.out.println("New Configuration created :P");
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void initialize() {
        configuration.logger = new LoggerConfiguration();
    }

    public int getPort() {
        return port;
    }

    public void loadYaml(String file) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        configuration = mapper.readValue(new File(file), Configuration.class);
        if(configuration.logger == null)
            configuration.logger = new LoggerConfiguration();
    }

    public String getLog() {
        return logger.toString();
    }
}
