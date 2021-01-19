package controller;

import context.RequestContext;
import context.ResponseContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JSONServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        JSONApplicationController controller = new JSONApplicationController();

        RequestContext requestContext = controller.getRequest(req);
        ResponseContext responseContext = null;
        try {
            responseContext = controller.handleRequest(requestContext);
            responseContext.setResponse(resp);
            controller.handleResponse(requestContext, responseContext);
        } catch (ControllerException e) {
            throw new ServletException(e);
        }
    }
}
