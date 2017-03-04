package ru.third.inno.task.common.listener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.third.inno.task.common.exception.UserDaoException;
import ru.third.inno.task.common.utils.Mailer;
import ru.third.inno.task.models.dao.UserDao;
import ru.third.inno.task.models.dao.UtilsDao;
import ru.third.inno.task.models.dao.iUserDao;
import ru.third.inno.task.models.dao.iUtilsDao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.IOException;

/**
 * Created by yy on 24.02.17.
 */
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener/*, ServletContextListener*/{

    Logger logger = Logger.getLogger(SessionListener.class);

    private iUserDao userDao;

    private iUtilsDao utilsDao;

    @Autowired
    public void setUtilsDao(iUtilsDao utilsDao) {
        this.utilsDao = utilsDao;
    }

    @Autowired
    public void setUserDao(iUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.trace("\r\nsession start");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        String eadress = null;

        logger.trace("\r\nListener event");

        if(event.getValue().toString().equals("admin") && event.getName().toString().equals("role") && utilsDao.getValueByName("send_mail").equals("1")){

                logger.trace("\ntrying to send mail for: ");
                logger.trace("\nattribute val: " + event.getValue());
                logger.trace("\nattribute name: " + event.getName());

            try {
                eadress = userDao.getEadressByName(event.getValue().toString())/*"grigoriiorloff@gmail.com"*/;
                logger.trace("\nemail: " + eadress);
                Mailer.sendMail("\nUser with admin rights has been entered", eadress);
            } catch (IOException e) {
                logger.error("\nsend mail fails");
            } catch (UserDaoException e) {
                logger.error("\ncant get admins email");
            }

        }

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }


    /*@Override
    public void contextInitialized(ServletContextEvent sce) {
        WebApplicationContextUtils
                .getRequiredWebApplicationContext(sce.getServletContext())
                .getAutowireCapableBeanFactory()
                .autowireBean(this);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }*/
}
