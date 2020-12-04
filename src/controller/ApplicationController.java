package controller;

import context.RequestContext;
import context.ResponseContext;

public interface ApplicationController {
    RequestContext getRequest(Object request);
    ResponseContext handleRequest(RequestContext requestContext);
    void handleResponse(RequestContext requestContext, ResponseContext responseContext);
}
