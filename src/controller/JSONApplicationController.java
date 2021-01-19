package controller;

import command.AbstractCommand;
import command.CommandException;
import command.CommandFactory;
import context.RequestContext;
import context.ResponseContext;
import context.WebRequestContext;
import context.WebResponseContext;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JSONApplicationController implements ApplicationController {
    @Override
    public RequestContext getRequest(Object request) {
        RequestContext requestContext = new WebRequestContext();

        requestContext.setRequest(request);

        return requestContext;
    }

    @Override
    public ResponseContext handleRequest(RequestContext requestContext) throws ControllerException {
        ResponseContext responseContext = null;

        try {
            AbstractCommand command = CommandFactory.getCommand(requestContext);
            command.init(requestContext);
            responseContext = command.execute(new WebResponseContext());
        } catch (CommandException e) {
            throw new ControllerException(e);
        }

        return responseContext;
    }

    @Override
    public void handleResponse(RequestContext requestContext, ResponseContext responseContext) throws ControllerException {
        HttpServletRequest request = (HttpServletRequest) requestContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) responseContext.getResponse();

        JSONObject jsonData = (JSONObject) responseContext.getResult();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        try {
            PrintWriter out = response.getWriter();
            out.print(jsonData.toString());
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new ControllerException(e);
        }

    }
}
