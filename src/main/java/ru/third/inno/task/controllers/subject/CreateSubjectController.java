package ru.third.inno.task.controllers.subject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.third.inno.task.services.SubjectService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yy on 06.03.17.
 */
@Controller
public class CreateSubjectController {
    Logger logger = Logger.getLogger(CreateSubjectController.class);

    SubjectService subjectService;

    @Autowired
    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @RequestMapping(value = "/newsubject", method = RequestMethod.GET)
    protected ModelAndView getCreateSubject() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", null);

        modelAndView.setViewName("/newsubject");

        return modelAndView;
    }

    @RequestMapping(value = "/newsubject", method = RequestMethod.POST)
    protected String postCreateSubject(@RequestParam(name="name") String name,
                                     @RequestParam(name="description") String description,
                                     @RequestParam(name="sphere") String sphere) {

        logger.trace("name: " + name + "\r\ndescription: " + description + "\r\nsphere" + sphere );
        subjectService.create(name, description, sphere);

        return "/subjects";
    }
}
