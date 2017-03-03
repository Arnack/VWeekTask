package ru.third.inno.task.common.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by yy on 24.02.17.
 */
public class ApplicationLoadListener implements ServletContextListener {
    Logger logger = Logger.getLogger(ApplicationLoadListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.trace("site started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.trace("site stopped");
    }
}
