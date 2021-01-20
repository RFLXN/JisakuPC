import db.dao.user.MySQLUserDao;

import java.net.URL;

public class Test {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.naver.com/test/test.html");
            System.out.println(url.getPath());
            System.out.println(url.getFile());
            System.out.println(url.getRef());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
