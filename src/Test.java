import db.dao.user.MySQLUserDao;

public class Test {
    public static void main(String[] args) {
        MySQLUserDao mySQLUserDao = new MySQLUserDao();

        try {
            mySQLUserDao.deleteUser("4");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
