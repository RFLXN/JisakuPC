package bean;

import java.io.Serializable;

public class DBConnectionInfo implements Serializable {
    private String host;
    private String userName;
    private String password;

    public DBConnectionInfo() {}

    public String getHost() {
        return host;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
