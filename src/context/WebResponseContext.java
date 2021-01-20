package context;

import javax.servlet.http.HttpServletResponse;

public class WebResponseContext implements ResponseContext {
    private Object result;
    private String target;

    private HttpServletResponse response;

    public WebResponseContext() {
    }

    @Override
    public Object getResult() {
        return result;
    }

    @Override
    public void setResult(Object bean) {
        this.result = bean;
    }

    @Override
    public String getTarget() {
        return target;
    }

    @Override
    public void setTarget(String transferInfo) {
        if(transferInfo.equals("index")) {
            this.target = "index.jsp";
        } else {
            this.target = "/WEB-INF/jsp/" + transferInfo + ".jsp";
        }

    }

    @Override
    public Object getResponse() {
        return response;
    }

    @Override
    public void setResponse(Object response) {
        this.response = (HttpServletResponse) response;
    }
}
