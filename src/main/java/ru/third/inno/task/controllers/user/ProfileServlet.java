package ru.third.inno.task.controllers.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.third.inno.task.common.exception.UserDaoException;
import ru.third.inno.task.common.utils.InitServlet;
import ru.third.inno.task.models.pojo.User;
import ru.third.inno.task.services.UserService;
import ru.third.inno.task.services.iUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yy on 25.02.17.
 * This servlet is for showing information to the user
 * Also user can change information and the password by sending post query
 */
@Component
public class ProfileServlet extends InitServlet {

    private static Logger logger = Logger.getLogger(ProfileServlet.class);

    iUserService userService;

    @Autowired
    public void setUserService(iUserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        try {
            HttpSession session = req.getSession(false);

            logger.trace("session id: " + session.getAttribute("id"));

            user = userService.getUserById(session.getAttribute("id").toString());

            logger.trace("trying to get user in profile servlet" + user);

            req.setAttribute("name", user.getLogin());
            req.setAttribute("description", user.getDescription());
        } catch (UserDaoException e) {

            req.setAttribute("message", "Cant find this user, sorry");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
            logger.error("error while getting user by id " + e);
        }
        req.setAttribute("user", user);
        req.getRequestDispatcher("/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String id = session.getAttribute("id").toString();
        String description = req.getParameter("description");
        String pass = req.getParameter("password");

        if(userService.updateUserDescription(id, description, pass)){
            resp.sendRedirect("/users");
        }else{
            logger.error("can not edit user");
            resp.sendRedirect("/error.jsp");
        }
    }
}