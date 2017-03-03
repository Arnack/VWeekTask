package ru.third.inno.task.controllers.user;

import ru.third.inno.task.models.dao.UserDao;

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
public class DeleteUserServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(UserDao.deleteUserById(req.getParameter("id"))){
            resp.sendRedirect("/users");
        }else{
            resp.sendRedirect("/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(UserDao.deleteUserById(req.getParameter("id"))){
            resp.sendRedirect("/users");
        }else{
            resp.sendRedirect("/error.jsp");
        }
    }
}
