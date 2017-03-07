package ru.third.inno.task.controllers.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.third.inno.task.common.exception.TaskDaoException;
import ru.third.inno.task.models.dao.iTaskDao;
import ru.third.inno.task.models.pojo.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by yy on 06.03.17.
 */
@Controller
public class ReadTaskController {
    Logger logger = Logger.getLogger(ReadTaskController.class);

    iTaskDao taskDao;

    @Autowired
    public void setTaskDao(iTaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @RequestMapping(value = "/tasks")
    protected ModelAndView readTasks(HttpServletRequest req) throws ServletException, IOException {
        List<Task> tasks = null;

        ModelAndView modelAndView = new ModelAndView();

        HttpSession session = req.getSession(false);
        String id = session.getAttribute("id").toString();

        try {
            tasks = taskDao.getAllTasks(id);
            logger.trace("after get all tasks" + tasks);
        } catch (TaskDaoException e) {
            logger.error("Error in read task servlet" + e);
            modelAndView.setViewName("redirect: /error");
            return modelAndView;
        }
        modelAndView.addObject("tasks", tasks);

        modelAndView.setViewName("/tasks");
        return modelAndView;
    }
}
