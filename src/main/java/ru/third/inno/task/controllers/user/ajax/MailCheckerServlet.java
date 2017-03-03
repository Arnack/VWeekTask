package ru.third.inno.task.controllers.user.ajax;

import org.apache.log4j.Logger;
import ru.third.inno.task.models.dao.UtilsDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yy on 27.02.17.
 * This servlet is used to change send_mail value in the utils Table
 * This value sets if mail notifications are enabled
 */
public class MailCheckerServlet extends HttpServlet {
    Logger logger = Logger.getLogger(MailCheckerServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.trace("get in mail checker received");

        String value = null;
        String v = req.getParameter("send_mail");
        value = UtilsDao.getValueByName("send_mail");
        resp.setContentType("text/plain");

        resp.getWriter().write(value);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String value = req.getParameter("value");

        logger.trace("from doPost mail checker");

        if(UtilsDao.setValueByName(value, "send_mail")){
            resp.getWriter().write("1");
        }else {
            resp.getWriter().write("0");
        }

    }
}
