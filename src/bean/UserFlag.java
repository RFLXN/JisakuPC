package bean;

public class UserFlag {
    private boolean isCorrectUser;
    private boolean isAdmin;
    private String userNo;

    public UserFlag() {
    }

    public boolean isCorrectUser() {
        return isCorrectUser;
    }

    public void setCorrectUser(boolean correctUser) {
        this.isCorrectUser = correctUser;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
}
