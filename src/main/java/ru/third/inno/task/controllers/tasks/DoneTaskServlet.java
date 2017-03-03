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
 * This servlet update instances in the Task table by invoking DAO
 */
public class DoneTaskServlet extends HttpServlet {
    Logger logger = Logger.getLogger(DoneTaskServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String userId = session.getAttribute("id").toString();

        String taskId = req.getParameter("id");

        if(TaskDao.updateTaskReadyness(userId, taskId, "1")){
            req.setAttribute("message", "You have finished the task!");
            resp.sendRedirect("/tasks");
        }else {
            logger.error("cant update the task at done task dao");
            resp.sendRedirect("/error.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
