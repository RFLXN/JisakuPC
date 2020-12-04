package context;

public interface ResponseContext {
    void setResult(Object bean);
    Object getResult();
    void setTarget(String transferInfo);
    String getTarget();
    void setResponse(Object response);
    Object getResponse();
}

