package ru.third.inno.task.controllers.user.ajax;

import org.apache.log4j.Logger;
import ru.third.inno.task.common.exception.UserDaoException;
import ru.third.inno.task.models.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yy on 26.02.17.
 * This servlet is for checking login "on flight"
 * It send SQL queries by DAO
 */
public class LoginCheckerServlet extends HttpServlet {
    Logger logger = Logger.getLogger(LoginCheckerServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String responseText = null;
        logger.trace("login: " + login);
        System.out.println(login);

        if(!(login.equals("")) && (login != null)){
            try {
                if (UserDao.getUserByName(login)){
                    logger.info("user finded");
                    responseText = "<p style='color: forestgreen;'>" + "This login is free to use" + "</p>";
                } else {

                    logger.info("no user");
                    responseText = "<p style='color: red;'>" + "This login is already taken" + "</p>";
                }
            } catch (UserDaoException e) {
                logger.error("cant get user");
            }
        }

        resp.setContentType("text/plain");
        if (responseText == null){responseText = "";}

        resp.getWriter().write(responseText);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
