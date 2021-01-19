package rakuten;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestSender {
    private final String url;

    public RequestSender(String url) {
        this.url = url;
    }

    private String sendRequest(String method) {
        HttpURLConnection connection = null;
        URL targetURL = null;
        BufferedReader reader = null;
        StringBuffer buffer = null;

        String resultData = "";

        try {
            targetURL = new URL(url);

            connection = (HttpURLConnection) targetURL.openConnection();
            connection.setRequestMethod(method);
            connection.connect();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            buffer = new StringBuffer();

            String data = "";
            while ((data = reader.readLine()) != null) {
                buffer.append(data);
            }

            resultData = buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultData;
    }

    public String get() {
        return sendRequest("GET");
    }

    public String post() {
        return sendRequest("POST");
    }
}
