package controller;

import context.RequestContext;
import context.ResponseContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontServlet extends javax.servlet.http.HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");

        ApplicationController controller = new WebApplicationController();

        RequestContext requestContext = controller.getRequest(req);
        ResponseContext responseContext = controller.handleRequest(requestContext);
        responseContext.setResponse(res);
        controller.handleResponse(requestContext, responseContext);
    }
}