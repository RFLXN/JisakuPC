package context;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class JSONRequestContext implements RequestContext {
    private HttpServletRequest request;
    private Map<String, String[]> parameters;

    public JSONRequestContext() {
    }

    @Override
    public String getCommandPath() {
        return request.getServletPath().substring(1);
    }

    @Override
    public String[] getParameter(String key) {
        return parameters.get(key);
    }

    @Override
    public Object getRequest() {
        return request;
    }

    @Override
    public void setRequest(Object request) {
        this.request = (HttpServletRequest) request;
        parameters = this.request.getParameterMap();
    }

    @Override
    public String getReferer() {
        return null;
    }

    public Object getSessionAttribute(String key) {
    	return null;
    }
}
