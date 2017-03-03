package ru.third.inno.task.controllers.additional;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yy on 26.02.17.
 */
public class ToggleNightServlet extends HttpServlet {
    Logger logger = Logger.getLogger(ToggleNightServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        StringBuffer requestURL = req.getRequestURL();
        String mode = req.getParameter("mode");
        session.setAttribute("night", mode);

        resp.getWriter().write(mode);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        String mode = req.getParameter("mode");

        session.setAttribute("night", mode);

        resp.sendRedirect("/index");
    }
}
