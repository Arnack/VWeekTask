package ru.third.inno.task.common.listener;

import org.apache.log4j.Logger;
import ru.third.inno.task.common.exception.UserDaoException;
import ru.third.inno.task.common.utils.Mailer;
import ru.third.inno.task.models.dao.UserDao;
import ru.third.inno.task.models.dao.UtilsDao;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.IOException;

/**
 * Created by yy on 24.02.17.
 */
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener{

    Logger logger = Logger.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.trace("session start");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        String eadress = null;

        if(event.getValue().toString().equals("admin") && event.getName().toString().equals("role") && UtilsDao.getValueByName("send_mail").equals("1")){

                logger.trace("trying to send mail for: ");
                logger.trace("attribute val: " + event.getValue());
                logger.trace("attribute name: " + event.getName());

            try {
                eadress = UserDao.getEadressByName(event.getValue().toString());
                logger.trace("email: " + eadress);
                Mailer.sendMail("User with admin rights has been entered", eadress);
            } catch (IOException e) {
                logger.error("send mail fails");
            } catch (UserDaoException e) {
                logger.error("cant get admins email");
            }

        }

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}
