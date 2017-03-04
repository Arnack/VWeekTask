package ru.third.inno.task.controllers.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.third.inno.task.common.utils.InitServlet;
import ru.third.inno.task.models.dao.BoardDao;
import ru.third.inno.task.models.dao.SubjectDao;
import ru.third.inno.task.models.dao.iBoardDao;
import ru.third.inno.task.models.dao.iSubjectDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yy on 24.02.17.
 * This servlet deletes Subject by id getted from jsp
 */
@Component
public class DeleteSubjectServlet extends InitServlet{

    iBoardDao boardDao;

    iSubjectDao subjectDao;

    @Autowired
    public void setSubjectDao(iSubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    @Autowired
    public void setBoardDao(iBoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(subjectDao.deleteSubjectById(req.getParameter("id"))){
            boardDao.deleteBoardAniway(req.getParameter("id"));
            resp.sendRedirect("/subjects");
        }else{
            resp.sendRedirect("/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(subjectDao.deleteSubjectById(req.getParameter("id"))){

            boardDao.deleteBoardAniway(req.getParameter("id"));

            resp.sendRedirect("/subjects");
        }else{
            resp.sendRedirect("/error.jsp");
        }
    }
}
