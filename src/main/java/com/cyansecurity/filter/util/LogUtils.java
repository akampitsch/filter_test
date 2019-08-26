package com.cyansecurity.filter.util;

import io.cucumber.core.api.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.time.LocalDateTime.now;

@SuppressWarnings ("StringBuilder")
public class LogUtils
{

    public static void info (Scenario scenario, Class clazz, String message)
    {
        final Logger logger = LogManager.getLogger (clazz);
        logger.info (message);
        String sb = now () + " " +
            "[" +
            Thread.currentThread ().getId () +
            "]" +
            " " +
            "INFO" +
            " " +
            clazz.getSimpleName () +
            " - " +
            message;
        scenario.write (sb);
    }

    public static void debug (Scenario scenario, Class clazz, String message)
    {
        final Logger logger = LogManager.getLogger (clazz);
        logger.debug (message);
        String sb = now () + " " +
            "[" +
            Thread.currentThread ().getId () +
            "]" +
            " " +
            "DEBUG" +
            " " +
            clazz.getSimpleName () +
            " - " +
            message;
        scenario.write (sb);
    }

    public static void error (Scenario scenario, Class clazz, String message)
    {
        final Logger logger = LogManager.getLogger (clazz);
        logger.error (message);
        String sb = now () + " " +
            "[" +
            Thread.currentThread ().getId () +
            "]" +
            " " +
            "ERROR" +
            " " +
            clazz.getSimpleName () +
            " - " +
            message;
        scenario.write (sb);
    }
}
