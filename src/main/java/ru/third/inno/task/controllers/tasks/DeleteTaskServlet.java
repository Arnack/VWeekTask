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
 * Deletes task with DAO by task id, using user's id for security
 */
public class DeleteTaskServlet extends HttpServlet {
    Logger logger = Logger.getLogger(DeleteTaskServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String userId = session.getAttribute("id").toString();
        String id = req.getParameter("id");

        if(TaskDao.deleteTaskById(userId, id)){
            resp.sendRedirect("/tasks");
        }else{
            resp.sendRedirect("/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String userId = session.getAttribute("id").toString();
        String id = req.getParameter("id");

        if(TaskDao.deleteTaskById(userId, id)){
            resp.sendRedirect("/tasks");
        }else{
            resp.sendRedirect("/error.jsp");
        }
    }
}
