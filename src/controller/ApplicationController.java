package controller;

import context.RequestContext;
import context.ResponseContext;

public interface ApplicationController {
    RequestContext getRequest(Object request);
    ResponseContext handleRequest(RequestContext requestContext) throws ControllerException;
    void handleResponse(RequestContext requestContext, ResponseContext responseContext) throws ControllerException;
}
