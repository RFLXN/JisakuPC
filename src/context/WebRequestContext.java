package context;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class WebRequestContext implements RequestContext {
    private HttpServletRequest request;
    private Map<String, String[]> parameters;

    @Override
    public String getCommandPath() {
        return request.getServletPath().substring(1);
    }

    @Override
    public String[] getParameter(String key) {
        return parameters.get(key);
    }

    @Override
    public void setRequest(Object request) {
        this.request = (HttpServletRequest)request;
        parameters = this.request.getParameterMap();
    }

    @Override
    public Object getRequest() {
        return request;
    }
}
