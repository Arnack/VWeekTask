package ru.third.inno.task.controllers.subject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.third.inno.task.common.exception.SubjectDaoException;
import ru.third.inno.task.models.pojo.Subject;
import ru.third.inno.task.services.SubjectService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yy on 06.03.17.
 */
@Controller
public class EditSubjectController {
    private static Logger logger = Logger.getLogger(EditSubjectController.class);

    SubjectService subjectService;


    @Autowired
    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    /**
     *
     * Sends information to the front
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/editsubject", method = RequestMethod.GET)
    protected ModelAndView getEditSubject(@RequestParam(name="id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        Subject subject = null;
        try {
            subject = subjectService.getSubjectById(Integer.valueOf(id));
            modelAndView.addObject("id", subject.getId());
            modelAndView.addObject("name", subject.getName());
            modelAndView.addObject("description", subject.getDescription());
            modelAndView.addObject("sphere", subject.getSphere());

            logger.trace("id " + subject.getId() + " login " + subject.getName() + " decription " +  subject.getDescription() + " " + subject.getSphere());
        } catch (SubjectDaoException e) {
            logger.error("error while getting subject by id " + e);
        }
        modelAndView.addObject("subject", subject);

        modelAndView.setViewName("editsubject");

        return modelAndView;
    }

    /**
     * Get info to update, then invokes DAO
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/editsubject", method = RequestMethod.GET)
    protected String postEditSubject(@RequestParam(name="id") String id,
                                           @RequestParam(name="name") String name,
                                           @RequestParam(name="description") String description,
                                           @RequestParam(name="sphere") String sphere) {


        if(subjectService.updateSubject(id, name, description, sphere)){
            return "/subjects";
        }else{
            logger.error("can not edit subject");
            return "/error.jsp";
        }
    }
}
