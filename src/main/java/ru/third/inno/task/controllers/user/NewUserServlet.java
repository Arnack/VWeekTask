package ru.third.inno.task.controllers.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.third.inno.task.common.exception.UserDaoException;
import ru.third.inno.task.common.utils.InitServlet;
import ru.third.inno.task.models.dao.UserDao;
import ru.third.inno.task.models.dao.iUserDao;
import ru.third.inno.task.services.UserService;
import ru.third.inno.task.services.iUserService;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by yy on 24.02.17.
 * This servlet is for creating new users by admin
 */
@Component
public class NewUserServlet  extends InitServlet {
    Logger logger = Logger.getLogger(NewUserServlet.class);

    iUserDao userDao;

    @Autowired
    public void setUserDao(iUserDao userDao) {
        this.userDao = userDao;
    }

    iUserService userService;

    @Autowired
    public void setUserService(iUserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", null);
        req.getRequestDispatcher("/newuser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            if (userDao.getUserByName(login)) {
                userService.registration(login, password);
            }else {
                String message = "This username is already exists, sorry\r\n <br> Please, try again with another one";
                req.setAttribute("message", message);
                getServletContext().getRequestDispatcher("/newuser.jsp").forward(req, resp);
            }
        } catch (UserDaoException e) {
            logger.error("error while adding new user");
        } catch (SQLException e) {
            logger.error(e);
        } catch (NamingException e) {
            logger.error("new user naming error" + e);
        }
        resp.sendRedirect("/users");
    }
}