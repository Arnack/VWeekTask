package ru.third.inno.task.controllers.subject;

import ru.third.inno.task.models.dao.SubjectDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yy on 24.02.17.
 * This servlet deletes Subject by id getted from jsp
 */
public class DeleteSubjectServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(SubjectDao.deleteSubjectById(req.getParameter("id"))){
            resp.sendRedirect("/subjects");
        }else{
            resp.sendRedirect("/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(SubjectDao.deleteSubjectById(req.getParameter("id"))){
            resp.sendRedirect("/subjects");
        }else{
            resp.sendRedirect("/error.jsp");
        }
    }
}
