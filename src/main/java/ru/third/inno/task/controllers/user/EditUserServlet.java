package ru.third.inno.task.controllers.user;

import org.apache.log4j.Logger;
import ru.third.inno.task.common.exception.UserDaoException;
import ru.third.inno.task.models.pojo.User;
import ru.third.inno.task.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yy on 24.02.17.
 * This servlet gets parameters to delete user
 * Then invokes DAO's delete user method
 */
public class EditUserServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(EditUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        try {
            user = UserService.getUserById(req.getParameter("id"));
            req.setAttribute("id", user.getId());
            req.setAttribute("login", user.getLogin());
            req.setAttribute("description", user.getDescription());
            req.setAttribute("password", user.getPassword());
            req.setAttribute("role", user.getRole());

            logger.trace("id " + user.getId() + " login " + user.getLogin() + " decription " +  user.getDescription() + " " + user.getPassword());
        } catch (UserDaoException e) {
            logger.error("error while getting user by id " + e);
        }
        req.setAttribute("user", user);
        System.out.println("user before sending: " + user);
        req.getRequestDispatcher("/edituser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        logger.trace("get for update - id: " + id);
        String login = req.getParameter("login");
        logger.trace("get for update - login: " + login);
        String password = req.getParameter("password");
        logger.trace("get for update - password: " + password);
        String description = req.getParameter("description");
        logger.trace("get for update - description: " + description);
        String role = req.getParameter("role");
        logger.trace("get for update - role: " + role);

        if(UserService.updateUser(id, login, password, description, role)){
            resp.sendRedirect("/users");
        }else{
            logger.error("can not edit user");
            resp.sendRedirect("/error.jsp");
        }
    }
}
