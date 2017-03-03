package ru.third.inno.task.controllers.subject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.third.inno.task.common.utils.InitServlet;
import ru.third.inno.task.models.dao.BoardDao;
import ru.third.inno.task.models.dao.iBoardDao;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//import org.springframework.core.env.PropertySource;

/**
 * Created by yy on 26.02.17.
 * This servlet invokes DAO to add instance to board DB table
 */
@Component
public class StartSubjectServlet extends InitServlet {
    Logger logger = Logger.getLogger(StartSubjectServlet.class);

    iBoardDao boardDao;

    @Autowired
    public void boardDao(iBoardDao boardDao) {
        this.boardDao = boardDao;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String userId = session.getAttribute("id").toString();
        String subjectId = req.getParameter("id");

        if(boardDao.addBoard(userId, subjectId)){
            req.setAttribute("message", "You starts learning the subject");
            resp.sendRedirect("/subjects");
        }else {
            resp.sendRedirect("/errorwithrights.jsp");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String userId = session.getAttribute("id").toString();
        String subjectId = req.getParameter("id");

        if(boardDao.addBoard(userId, subjectId)){
            req.setAttribute("message", "You starts learning the subject");
            resp.sendRedirect("/subjects");
        }else {
            resp.sendRedirect("/errorwithrights.jsp");
        }
    }
}
