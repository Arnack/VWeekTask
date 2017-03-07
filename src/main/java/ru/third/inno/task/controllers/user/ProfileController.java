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
public class ProfileController {
    private static Logger logger = Logger.getLogger(ProfileController.class);

    iUserService userService;

    @Autowired
    public void setUserService(iUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    protected ModelAndView getProfile(HttpServletRequest req) {
        User user = null;
        ModelAndView modelAndView = new ModelAndView();
        try {
            HttpSession session = req.getSession(false);
            logger.trace("session id: " + session.getAttribute("id"));

            user = userService.getUserById(session.getAttribute("id").toString());
            logger.trace("trying to get user in profile servlet" + user);

            modelAndView.addObject("name", user.getLogin());
            modelAndView.addObject("description", user.getDescription());
        } catch (UserDaoException e) {

            modelAndView.addObject("message", "Cant find this user, sorry");

            logger.error("error while getting user by id " + e);
            modelAndView.setViewName("/error.jsp");
            return modelAndView;

        }
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/profile");

        return modelAndView;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    protected void postProfile(HttpServletRequest req,
                               HttpServletResponse resp,
                               Model model,
                               @RequestParam(name="namex") String login,
                               @RequestParam(name="description") String description,
                               @RequestParam(name="password") String password
    ){
        HttpSession session = req.getSession(false);

        String id = session.getAttribute("id").toString();

        String hashedPass = Salter.toSalt(login, password);

        if(userService.updateUserDescription(id, description, hashedPass)){

            String message = "Your profile successfully updated";
            req.setAttribute("message", message);

            try {
                resp.getWriter().write("1");
            } catch (IOException e) {
                logger.error("cant send info: " + e);
            }

        }else{
            logger.error("can not edit user");
            try {
                resp.getWriter().write("0");
            } catch (IOException e) {
                logger.error("cant send info: " + e);
            }
        }
    }
}
