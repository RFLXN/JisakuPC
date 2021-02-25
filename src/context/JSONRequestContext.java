package context;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

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
    }

    @Override
    public String getReferer() {
        return null;
    }

    @Override
    public Object getSessionAttribute(String key) {
        return null;
    }

    @Override
    public void setSessionAttribute(String key, Object value) {
    }

    @Override
    public void clearSession() {

    }
}
