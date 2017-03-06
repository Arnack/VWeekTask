package ru.third.inno.task.controllers.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.third.inno.task.common.utils.InitServlet;
import ru.third.inno.task.models.dao.iBoardDao;
import ru.third.inno.task.models.dao.iSubjectDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yy on 06.03.17.
 */
public class DeleteSubjectController {

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

    @RequestMapping(value = "/deletesubject")
        protected String deletesubject(@RequestParam(name="id") String id) {
            if (subjectDao.deleteSubjectById(id)) {
                boardDao.deleteBoardAniway(id);
                return "/subjects";
            } else {
                return "/error.jsp";
            }
        }
}
