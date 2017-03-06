package ru.third.inno.task.controllers.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.third.inno.task.common.exception.UserDaoException;
import ru.third.inno.task.common.utils.Salter;
import ru.third.inno.task.models.dao.UserDao;
import ru.third.inno.task.models.pojo.User;
import ru.third.inno.task.services.UserService;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by yy on 06.03.17.
 */
@Controller
public class RegisterController {
    Logger logger = Logger.getLogger(RegisterController.class);

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


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegistration(Model model){

        model.addAttribute("message", null);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView postRegistration(HttpServletRequest req,
                                         @RequestParam(name="login") String login,
                                         @RequestParam(name="password") String password ){

        HttpSession session = req.getSession();
        ModelAndView modelAndView = new ModelAndView();

        try {
            if (userDao.getUserByName(login)){
                String hashedpass = Salter.toSalt(login, password);

                logger.trace(hashedpass);
                userService.registration(login, hashedpass);

                User user = userService.getUserByLoginAndPassword(login, hashedpass);

                session.setAttribute("id", user.getId());
                session.setAttribute("name", user.getLogin());
                session.setAttribute("role", user.getRole());
                modelAndView.setViewName("/index");
                session.setMaxInactiveInterval(7*24*60*60);
            }else{
                String message = "This username is already exists, sorry\r\n <br> Please, try again with another one";

                modelAndView.addObject("message", message);
                modelAndView.setViewName("/register");
            }
        } catch (UserDaoException e) {
            logger.error("cant check if user exists");
            modelAndView.setViewName("redirect: /error");
        } catch (SQLException e) {
            logger.error(e);
        } catch (NamingException e) {
            logger.error(e);
        }

        return modelAndView;
    }
}
