package ru.third.inno.task.controllers.subject;

import org.apache.log4j.Logger;
import ru.third.inno.task.common.exception.SubjectDaoException;
import ru.third.inno.task.models.pojo.Subject;
import ru.third.inno.task.services.SubjectService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yy on 24.02.17.
 * This servlet sends information to front by get qury
 * And gets ifo by post query
 */
public class EditSubjectServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(EditSubjectServlet.class);

    /**
     *
     * Sends information to the front
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Subject subject = null;
        try {
            subject = SubjectService.getSubjectById(Integer.valueOf(req.getParameter("id")));
            req.setAttribute("id", subject.getId());
            req.setAttribute("name", subject.getName());
            req.setAttribute("description", subject.getDescription());
            req.setAttribute("sphere", subject.getSphere());

            logger.trace("id " + subject.getId() + " login " + subject.getName() + " decription " +  subject.getDescription() + " " + subject.getSphere());
        } catch (SubjectDaoException e) {
            logger.error("error while getting subject by id " + e);
        }
        req.setAttribute("subject", subject);
        System.out.println("subject before sending: " + subject);
        req.getRequestDispatcher("/editsubject.jsp").forward(req, resp);
    }

    /**
     * Get info to update, then invokes DAO
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        logger.trace("get for update - id: " + id);
        String name = req.getParameter("name");
        logger.trace("get for update - name: " + name);
        String description = req.getParameter("description");
        logger.trace("get for update - description: " + description);
        String sphere = req.getParameter("sphere");
        logger.trace("get for update - sphere: " + sphere);

        if(SubjectService.updateSubject(id, name, description, sphere)){
            resp.sendRedirect("/subjects");
        }else{
            logger.error("can not edit subject");
            resp.sendRedirect("/error.jsp");
        }
    }
}
