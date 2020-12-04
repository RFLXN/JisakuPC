package context;

public interface RequestContext {
    String getCommandPath();
    String[] getParameter(String key);
    void setRequest(Object request);
    Object getRequest();
}
