package filter;

import bean.UserFlag;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        UserFlag loginFlag = (UserFlag) request.getSession().getAttribute("loginFlag");

        if (loginFlag == null || (!loginFlag.isCorrectUser())) {
            request.getSession().setAttribute("source", request.getServletPath().substring(1));

            servletRequest.getRequestDispatcher("/login").forward(servletRequest, servletResponse);
        } else if (!loginFlag.isAdmin()) {
            throw new ServletException("Admin Only Page!");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
