package io.github.leordev.server;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * Created by lribeiro on 11/11/16.
 */
public class LoggerConfiguration {
    ConsoleAppender console;

    public LoggerConfiguration() {
        // configure the appender
        console = new ConsoleAppender();
        String PATTERN = "%d [%p|%c|%C{1}] %m%n";
        console.setLayout(new PatternLayout(PATTERN));
        console.setThreshold(Level.INFO);
        console.activateOptions();

        // add appender to any Logger (here is root)
        Logger.getRootLogger().addAppender(console);
    }
}
