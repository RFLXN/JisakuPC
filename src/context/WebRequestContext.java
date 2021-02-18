package context;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class WebRequestContext implements RequestContext {
    private HttpServletRequest request;
    private Map<String, String[]> parameters;
    private String referer;

    public WebRequestContext() {
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

        try {
            String refererHeader = this.request.getHeader("Referer");
            String[] ref = refererHeader.split("/");
            referer = ref[ref.length - 1];
            System.out.println(referer);
        } catch (NullPointerException e) {
            referer = null;
        }
    }

    @Override
    public String getReferer() {
        return referer;
    }

    public Object getSessionAttribute(String key) {
    	return (Object) request.getSession().getAttribute(key);
    }
}
