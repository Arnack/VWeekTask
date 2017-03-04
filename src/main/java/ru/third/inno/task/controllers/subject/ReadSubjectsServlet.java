package ru.third.inno.task.controllers.subject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.third.inno.task.common.utils.InitServlet;
import ru.third.inno.task.models.pojo.Subject;
import ru.third.inno.task.services.SubjectService;
import ru.third.inno.task.services.iSubjectService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by yy on 24.02.17.
 */
@Component
public class ReadSubjectsServlet extends InitServlet {
    Logger logger = Logger.getLogger(ReadSubjectsServlet.class);

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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Subject> subjects = null;

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
        req.setAttribute("subjects", subjects);

        List<Subject> userSubjects = null;
        /**
         * all user subjects
         */
        userSubjects = subjectService.getAllUserSubjects(id);


        req.setAttribute("userSubjects", userSubjects);

        req.getRequestDispatcher("/subjects.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
