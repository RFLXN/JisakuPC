package controller;

import context.RequestContext;
import context.ResponseContext;
import db.dao.user.MySQLUserDao;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JSONServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
        /*
        req.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");

        MySQLUserDao dao = new MySQLUserDao();
        boolean al = true;
        try {
            al = dao.isAlreadyUsedId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isAlreadyUsedId", al);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(jsonObject.toString());
        out.flush();
        out.close();
        */
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
