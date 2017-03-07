package ru.third.inno.task.controllers.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.third.inno.task.models.dao.TaskDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yy on 06.03.17.
 */
@Controller
public class CreateTaskController {
    Logger logger = Logger.getLogger(CreateTaskController.class);

    TaskDao taskDao;

    @Autowired
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @RequestMapping(value = "/newtask", method = RequestMethod.GET)
    protected ModelAndView getNewTask() throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", null);

        modelAndView.setViewName("/newtask");

        return modelAndView;
    }

    @RequestMapping(value = "/newtask", method = RequestMethod.POST)
    protected ModelAndView postNewTask(HttpServletRequest req,
                                       @RequestParam(name="name") String name,
                                       @RequestParam(name="description") String description
    ) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();

        HttpSession session = req.getSession(false);
        String id = session.getAttribute("id").toString();

        if (taskDao.newTask(name, description, id)){
            logger.trace("new task success");
            modelAndView.addObject("message", "New task successfully created");

            modelAndView.setViewName("/tasks");
            return modelAndView;
        }else {
            logger.error("error while adding new task");
            modelAndView.setViewName("/error");
            return modelAndView;
        }

    }
}
