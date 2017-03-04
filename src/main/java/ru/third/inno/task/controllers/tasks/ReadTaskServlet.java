package ru.third.inno.task.controllers.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.third.inno.task.common.exception.TaskDaoException;
import ru.third.inno.task.common.utils.InitServlet;
import ru.third.inno.task.models.dao.TaskDao;
import ru.third.inno.task.models.dao.iTaskDao;
import ru.third.inno.task.models.pojo.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by yy on 25.02.17.
 * This srevlet is for getting all tasks
 */
@Component
public class ReadTaskServlet extends InitServlet {

    Logger logger = Logger.getLogger(ReadTaskServlet.class);

    iTaskDao taskDao;

    @Autowired
    public void setTaskDao(iTaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Task> tasks = null;

        HttpSession session = req.getSession(false);
        String id = session.getAttribute("id").toString();

        try {
            tasks = taskDao.getAllTasks(id);
            logger.trace("after get all tasks" + tasks);
        } catch (TaskDaoException e) {
            logger.error("Error in read task servlet" + e);
            resp.sendRedirect("/error.jsp");
        }
        req.setAttribute("tasks", tasks);

        req.getRequestDispatcher("/tasks.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
