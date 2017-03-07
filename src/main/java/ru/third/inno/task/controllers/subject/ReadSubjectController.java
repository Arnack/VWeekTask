package ru.third.inno.task.controllers.subject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.third.inno.task.models.pojo.Subject;
import ru.third.inno.task.services.iSubjectService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by yy on 07.03.17.
 */
public class ReadSubjectController {
    Logger logger = Logger.getLogger(ReadSubjectController.class);

    iSubjectService subjectService;

    @Autowired
    public void setSubjectService(iSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    /**
     * Gets All user's subjects and others subjects
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/subjects")
    protected ModelAndView readsubjects(HttpServletRequest req) throws ServletException {
        List<Subject> subjects = null;
        ModelAndView modelAndView = new ModelAndView();

        /**
         * gets all subjects
         * subjects = SubjectService.getAllSubjects();
         */
        HttpSession session = req.getSession(false);

        int id = (int) session.getAttribute("id");

        logger.trace("get session id in the logger servlet: " + id);

        /**
         * gets not users subjects
         */
        subjects = subjectService.getAllNotUserSubjects(id);
        modelAndView.addObject("subjects", subjects);

        List<Subject> userSubjects = null;
        /**
         * all user subjects
         */
        userSubjects = subjectService.getAllUserSubjects(id);

        modelAndView.addObject("userSubjects", userSubjects);

        modelAndView.setViewName("/subjects");
        return modelAndView;
    }

}
