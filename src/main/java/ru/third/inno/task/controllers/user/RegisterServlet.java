package ru.third.inno.task.controllers.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.third.inno.task.common.exception.UserDaoException;
import ru.third.inno.task.common.utils.InitServlet;
import ru.third.inno.task.common.utils.Salter;
import ru.third.inno.task.models.dao.UserDao;
import ru.third.inno.task.models.pojo.User;
import ru.third.inno.task.services.UserService;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by yy on 23.02.17.
 * This method is for creating new user by registration
 * It gets login and password
 * If registration succeed it creates session else it send message to the front
 */

@Component
public class RegisterServlet extends InitServlet {
    Logger logger = Logger.getLogger(RegisterServlet.class);

    UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", null);
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");

        String password = req.getParameter("password");

        try {
            if (userDao.getUserByName(login)){
                String hashedpass = Salter.toSalt(login, password);

                logger.trace(hashedpass);
                userService.registration(login, hashedpass);

                User user = userService.getUserByLoginAndPassword(login, hashedpass);

                session.setAttribute("id", user.getId());
                session.setAttribute("name", user.getLogin());
                session.setAttribute("role", user.getRole());
                resp.sendRedirect("/index");
                session.setMaxInactiveInterval(7*24*60*60);
            }else{
                String message = "This username is already exists, sorry\r\n <br> Please, try again with another one";
                req.setAttribute("message", message);
                getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);
            }
        } catch (UserDaoException e) {
            logger.error("cant check if user exists");
            resp.sendRedirect("/error.jsp");
        } catch (SQLException e) {
            logger.error(e);
        } catch (NamingException e) {
            e.printStackTrace();
        }


    }
}
