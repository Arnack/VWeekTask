package ru.third.inno.task.controllers.user.ajax;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.third.inno.task.common.utils.InitServlet;
import ru.third.inno.task.models.dao.UtilsDao;
import ru.third.inno.task.models.dao.iUtilsDao;

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
@Component
public class MailCheckerServlet extends InitServlet {
    Logger logger = Logger.getLogger(MailCheckerServlet.class);

    iUtilsDao utilsDao;

    @Autowired
    public void setUtilsDao(iUtilsDao utilsDao) {
        this.utilsDao = utilsDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.trace("get in mail checker received");

        String value = null;
        String v = req.getParameter("send_mail");
        value = utilsDao.getValueByName("send_mail");
        resp.setContentType("text/plain");

        resp.getWriter().write(value);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String value = req.getParameter("value");

        logger.trace("from doPost mail checker");

        if(utilsDao.setValueByName(value, "send_mail")){
            resp.getWriter().write("1");
        }else {
            resp.getWriter().write("0");
        }

    }
}
