package ru.third.inno.task.controllers.subject;

import org.apache.log4j.Logger;
import ru.third.inno.task.services.SubjectService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yy on 24.02.17.
 * This servlet is for creating tasks, it gets parameters by post query
 * And invokes a DB writer
 */
public class CreateSubjectServlet extends HttpServlet {
    Logger logger = Logger.getLogger(CreateSubjectServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", null);
        req.getRequestDispatcher("/newsubject.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String sphere = req.getParameter("sphere");

        SubjectService.create(name, description, sphere);

        resp.sendRedirect("/subjects");
    }
}