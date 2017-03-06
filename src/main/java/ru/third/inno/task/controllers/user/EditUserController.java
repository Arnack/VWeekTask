package ru.third.inno.task.controllers.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.third.inno.task.common.exception.UserDaoException;
import ru.third.inno.task.models.pojo.User;
import ru.third.inno.task.services.iUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yy on 06.03.17.
 */
@Controller
public class EditUserController {
    private static Logger logger = Logger.getLogger(EditUserController.class);

    iUserService userService;

    @Autowired
    public void setUserService(iUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/edituser", method = RequestMethod.GET)
    protected ModelAndView getEditUser(HttpServletRequest req,
                                       @RequestParam(name="id") String id) {
        User user = null;
        ModelAndView modelAndView = new ModelAndView();
        try {
            user = userService.getUserById(id);

            modelAndView.addObject("id", user.getId());
            modelAndView.addObject("login", user.getLogin());
            modelAndView.addObject("description", user.getDescription());
            modelAndView.addObject("password", user.getPassword());
            modelAndView.addObject("role", user.getRole());

            logger.trace("id " + user.getId() + " login " + user.getLogin() + " decription " +  user.getDescription() + " " + user.getPassword());
        } catch (UserDaoException e) {
            logger.error("error while getting user by id " + e);
        }
        modelAndView.addObject("user", user);

        modelAndView.setViewName("/edituser");

        return modelAndView;
    }

    @RequestMapping(value = "/edituser", method = RequestMethod.POST)
    protected ModelAndView postEditUser(
            @RequestParam(name="id") String id,
            @RequestParam(name="login") String login,
            @RequestParam(name="password") String password,
            @RequestParam(name="description") String description,
            @RequestParam(name="role") String role
    ){
        ModelAndView modelAndView = new ModelAndView();

        if(userService.updateUser(id, login, password, description, role)){
            modelAndView.setViewName("redirect: /users");
        }else{
            logger.error("can not edit user");
            modelAndView.setViewName("redirect: /error");
        }
        modelAndView.setViewName("redirect: /users");
        return modelAndView;
    }
}
