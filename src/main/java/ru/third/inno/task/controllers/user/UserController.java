package ru.third.inno.task.controllers.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.third.inno.task.models.pojo.User;
import ru.third.inno.task.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by yy on 06.03.17.
 */
@Controller
public class UserController {

    Logger logger = Logger.getLogger(UserController.class);

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    protected ModelAndView getUser() {
        List<User> users = null;
        try {
            users = userService.getAllUsers();
        } catch (ClassNotFoundException e) {

        } catch (IllegalAccessException e) {
            logger.error(e);
        } catch (InstantiationException e) {
            logger.error(e);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);

        modelAndView.setViewName("/users");

        return  modelAndView;
    }

}
