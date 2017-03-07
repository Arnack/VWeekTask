package ru.third.inno.task.controllers.additional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yy on 07.03.17.
 */
@Controller
public class ToggleNightController {
    Logger logger = Logger.getLogger(ToggleNightController.class);


    @RequestMapping(value = "/togglenight", method = RequestMethod.GET)
    protected void getToggleNight(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        StringBuffer requestURL = req.getRequestURL();
        String mode = req.getParameter("mode");
        session.setAttribute("night", mode);

        resp.getWriter().write(mode);
    }

    @RequestMapping(value = "/togglenight", method = RequestMethod.POST)
    protected String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        String mode = req.getParameter("mode");

        session.setAttribute("night", mode);

        return "/index";
    }
}
