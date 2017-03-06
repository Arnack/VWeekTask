package ru.third.inno.task.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.third.inno.task.models.dao.iUserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yy on 06.03.17.
 */
@Controller
public class DeleteUserController {
    iUserDao userDao;

    @Autowired
    public void setUserDao(iUserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value = "/deleteuser")
    protected String getDeleteUser(@RequestParam(name="id") String id) {
        if(userDao.deleteUserById(id)){
            return "/users";
        }else{
            return "error";
        }
    }
}
