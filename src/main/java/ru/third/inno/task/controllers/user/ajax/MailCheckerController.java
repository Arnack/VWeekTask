package ru.third.inno.task.controllers.user.ajax;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.third.inno.task.common.utils.InitServlet;
import ru.third.inno.task.models.dao.iUtilsDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yy on 06.03.17.
 */
@Controller
public class MailCheckerController {
        Logger logger = Logger.getLogger(ru.third.inno.task.controllers.user.ajax.MailCheckerController.class);

        iUtilsDao utilsDao;

        @Autowired
        public void setUtilsDao(iUtilsDao utilsDao) {
            this.utilsDao = utilsDao;
        }

        @RequestMapping(value = "/mailchecker", method = RequestMethod.GET)
        protected void getMailChecker(
                @RequestParam(name="send_mail") String v,
                HttpServletResponse resp
        ) throws IOException {

            logger.trace("get in mail checker received");

            String value;
            value = utilsDao.getValueByName("send_mail");
            resp.setContentType("text/plain");
            resp.getWriter().write(value);
        }

        @RequestMapping(value = "/mailchecker", method = RequestMethod.POST)
        protected void postMailChecker(
                @RequestParam(name="value") String value,
                HttpServletResponse resp
        ) throws ServletException, IOException {

            logger.trace("from doPost mail checker");

            if(utilsDao.setValueByName(value, "send_mail")){
                resp.getWriter().write("1");
            }else {
                resp.getWriter().write("0");
            }
    }
}
