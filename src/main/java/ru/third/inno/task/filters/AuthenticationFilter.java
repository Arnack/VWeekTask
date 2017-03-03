package ru.third.inno.task.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yy on 25.02.17.
 */
public class AuthenticationFilter implements Filter {
    Logger logger = Logger.getLogger(AuthenticationFilter.class);

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        logger.trace("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        logger.trace("messege from filter");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        if(session == null || session.getAttribute("name") == null){
            logger.trace("Session is null");
            res.sendRedirect("/login.jsp");
        }else{
            logger.trace("chain session ");
            logger.trace("session is not null");
            chain.doFilter(request, response);

        }
    }

    public void destroy() {
        logger.trace("call for destroy");
        //close any resources here
    }

}
