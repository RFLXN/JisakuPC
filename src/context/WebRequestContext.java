package context;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

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
    public String[] getParameterKeys() {
        Set<String> keySet = parameters.keySet();

        String[] keys = new String[keySet.size()];

        int i = 0;
        for (String s : keySet) {
            keys[i++] = s;
        }

        return keys;
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

    @Override
    public Object getSessionAttribute(String key) {
        return request.getSession().getAttribute(key);
    }

    @Override
    public void setSessionAttribute(String key, Object value) {
        request.getSession().setAttribute(key, value);
    }
}
