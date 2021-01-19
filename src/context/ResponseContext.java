package context;

public interface ResponseContext {
    Object getResult();

    void setResult(Object bean);

    String getTarget();

    void setTarget(String transferInfo);

    Object getResponse();

    void setResponse(Object response);
}

