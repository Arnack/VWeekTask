package ru.third.inno.task.controllers.user;

import org.apache.log4j.Logger;
import ru.third.inno.task.common.exception.UserDaoException;
import ru.third.inno.task.models.dao.UserDao;
import ru.third.inno.task.services.UserService;

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
public class NewUserServlet  extends HttpServlet {
    Logger logger = Logger.getLogger(NewUserServlet.class);
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
            if (UserDao.getUserByName(login)) {
                UserService.registration(login, password);
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