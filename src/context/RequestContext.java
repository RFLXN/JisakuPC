package context;

public interface RequestContext {
    String getCommandPath();

    String[] getParameter(String key);

    Object getRequest();

    String getReferer();

    void setRequest(Object request);
}
