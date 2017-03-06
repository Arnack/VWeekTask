package ru.third.inno.task.controllers.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.third.inno.task.common.utils.Salter;
import ru.third.inno.task.models.pojo.User;
import ru.third.inno.task.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yy on 06.03.17.
 */
@Controller
public class LoginController {
    Logger logger = Logger.getLogger(LoginController.class);
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    protected String getLogin() {
        logger.trace("onget");
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    protected ModelAndView postLogin(HttpServletRequest req,
                                     HttpServletResponse resp,
                                     @RequestParam(name="login") String login,
                                     @RequestParam(name="password") String password,
                                     Model model
                            ) throws ServletException, IOException {

        ModelAndView modelAndView = new ModelAndView();
        String hashedpass = Salter.toSalt(login, password);
        logger.trace("salt: " + hashedpass);
        User user = userService.getUserByLoginAndPassword(login, hashedpass);

        if (user == null){user = userService.getUserByLoginAndPassword(login, password);}

        if(user != null){
            HttpSession session = req.getSession();

            session.setAttribute("id", user.getId());
            session.setAttribute("name", user.getLogin());
            session.setAttribute("role", user.getRole());
            session.setMaxInactiveInterval(7*24*60*60);

            modelAndView.setViewName("redirect: /index");
        }else{
            logger.trace("false");
            String message = "Login and password are incorrect, sorry\r\n " +
                    "<br> Please, check them and try again";

            modelAndView.addObject("message", message);
            modelAndView.setViewName("/login");
        }
        return modelAndView;
    }
}
