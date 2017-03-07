package ru.third.inno.task.controllers.subject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.third.inno.task.models.dao.iBoardDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yy on 07.03.17.
 */
public class StartSubjectController {
    Logger logger = Logger.getLogger(StartSubjectController.class);

    iBoardDao boardDao;

    @Autowired
    public void boardDao(iBoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @RequestMapping(value = "/startsubject")
    protected ModelAndView doGet(HttpServletRequest req, @RequestParam(name="id") String id) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = req.getSession(false);
        String userId = session.getAttribute("id").toString();
        String subjectId = id;

        if(boardDao.addBoard(userId, subjectId)){
            modelAndView.addObject("message", "You starts learning the subject");
            modelAndView.setViewName("redirect: /subjects");
        }else {
            modelAndView.setViewName("redirect: /errorwithrights");
        }
        return modelAndView;
    }
}
