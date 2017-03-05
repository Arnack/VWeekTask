package ru.third.inno.task.controllers.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.third.inno.task.common.utils.InitServlet;
import ru.third.inno.task.common.utils.Salter;
import ru.third.inno.task.models.pojo.User;
import ru.third.inno.task.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yy on 25.02.17.
 * This servlet is used to set attributes to session,
 * also it checks if user with such password and login is exist
 */
@Component
public class LoginServlet extends InitServlet {
    Logger logger = Logger.getLogger(LoginServlet.class);

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("onget");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        logger.trace("forsalt: " + password);
        System.out.println("forsalt: " + password);

        String hashedpass = Salter.toSalt(login, password);

        logger.trace("salt: " + hashedpass);
        System.out.println("salt: " + hashedpass);

        User user = userService.getUserByLoginAndPassword(login, hashedpass);

        if (user == null){user = userService.getUserByLoginAndPassword(login, password);}

        if(user != null){
            HttpSession session = req.getSession();

            session.setAttribute("id", user.getId());
            session.setAttribute("name", user.getLogin());
            session.setAttribute("role", user.getRole());
            session.setMaxInactiveInterval(7*24*60*60);

            resp.sendRedirect("/index");
        }else{
            logger.trace("false");
            String message = "Login and password are incorrect, sorry\r\n " +
                    "<br> Please, check them and try again";
            req.setAttribute("message", message);
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}