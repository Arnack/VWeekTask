package ru.third.inno.task.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.third.inno.task.common.utils.InitServlet;
import ru.third.inno.task.models.dao.UserDao;
import ru.third.inno.task.models.dao.iUserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yy on 24.02.17.
 * This servlet gets userId to delete
 * Then invokes DAO's delete user method
 */
@Component
public class DeleteUserServlet extends InitServlet{

    iUserDao userDao;

    @Autowired
    public void setUserDao(iUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(userDao.deleteUserById(req.getParameter("id"))){
            resp.sendRedirect("/users");
        }else{
            resp.sendRedirect("/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(userDao.deleteUserById(req.getParameter("id"))){
            resp.sendRedirect("/users");
        }else{
            resp.sendRedirect("/error.jsp");
        }
    }
}
