package ru.third.inno.task.controllers.tasks;

import org.apache.log4j.Logger;
import ru.third.inno.task.models.dao.TaskDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yy on 26.02.17.
 * This servlet gets information to add to DB with DAO by POST qery
 */
public class CreateTaskServlet extends HttpServlet {
    Logger logger = Logger.getLogger(CreateTaskServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", null);
        req.getRequestDispatcher("/newtask.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String id = session.getAttribute("id").toString();

        String name = req.getParameter("name");
        String description = req.getParameter("description");

        if (TaskDao.newTask(name, description, id)){
            logger.trace("new task success");
            req.setAttribute("message", "New task successfully created");
            resp.sendRedirect("/tasks");
        }else {
            logger.error("error while adding new task");
            resp.sendRedirect("/error.jsp");
        }
    }
}
