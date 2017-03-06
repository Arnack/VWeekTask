package ru.third.inno.task.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yy on 06.03.17.
 */
@Controller
public class LogoutController {

    @RequestMapping(value = "/register")
    protected String logout(HttpServletRequest req) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        return "/index";
    }
}
