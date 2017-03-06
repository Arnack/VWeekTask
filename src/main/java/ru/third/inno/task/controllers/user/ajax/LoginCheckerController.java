package ru.third.inno.task.controllers.user.ajax;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.third.inno.task.common.exception.UserDaoException;
import ru.third.inno.task.models.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yy on 06.03.17.
 */
@Controller
public class LoginCheckerController {
    Logger logger = Logger.getLogger(LoginCheckerController.class);

    UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value = "/loginchecker")
    protected void logincheck(@RequestParam(name="login") String login,
                              HttpServletResponse resp) throws ServletException, IOException {

        String responseText = null;
        logger.trace("login: " + login);

        if(!(login.equals("")) && (login != null)){
            try {
                if (userDao.getUserByName(login)){
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

}
