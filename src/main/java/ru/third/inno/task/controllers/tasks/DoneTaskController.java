package ru.third.inno.task.controllers.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
public class DoneTaskController {
    Logger logger = Logger.getLogger(DoneTaskController.class);

    TaskDao taskDao;

    @Autowired
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @RequestMapping(value = "/donetask")
    protected String doGet(HttpServletRequest req, @RequestParam(name="id") String id) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String userId = session.getAttribute("id").toString();

        if(taskDao.updateTaskReadyness(userId, id, "1")){
            req.setAttribute("message", "You have finished the task!");
            return "/tasks";
        }else {
            logger.error("cant update the task at done task dao");
           return "/error";
        }

    }
}
