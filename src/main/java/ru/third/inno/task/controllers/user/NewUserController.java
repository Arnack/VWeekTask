package ru.third.inno.task.controllers.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.third.inno.task.common.exception.UserDaoException;
import ru.third.inno.task.models.dao.iUserDao;
import ru.third.inno.task.services.iUserService;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by yy on 06.03.17.
 */
public class NewUserController {

    Logger logger = Logger.getLogger(NewUserController.class);

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

    @RequestMapping(value = "/newuser", method = RequestMethod.GET)
    protected ModelAndView getNewUser(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", null);
        modelAndView.setViewName("/newuser");
        return modelAndView;
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    protected ModelAndView postNewUser( @RequestParam(name="login") String login,
                                        @RequestParam(name="password") String password) {

        ModelAndView modelAndView = new ModelAndView();
        try {
            if (userDao.getUserByName(login)) {
                userService.registration(login, password);
            }else {
                String message = "This username is already exists, sorry\r\n <br> Please, try again with another one";
                modelAndView.addObject("message", message);
                modelAndView.setViewName("/newuser");
                return modelAndView;
            }
        } catch (UserDaoException e) {
            logger.error("error while adding new user");
            modelAndView.setViewName("/error");
            return modelAndView;
        } catch (SQLException e) {
            logger.error(e);
            modelAndView.setViewName("/error");
            return modelAndView;
        } catch (NamingException e) {
            logger.error("new user naming error" + e);
            modelAndView.setViewName("/error");
            return modelAndView;
        }
        modelAndView.setViewName("/newuser");
        return modelAndView;
    }

}
