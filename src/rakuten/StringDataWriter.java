package rakuten;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StringDataWriter {
    private final BufferedOutputStream out;

    public StringDataWriter(String fileName) throws IOException {
        out = new BufferedOutputStream(new FileOutputStream(fileName));
    }

    public void write(String data) throws IOException {
        out.write(data.getBytes());
    }

    public void close() throws IOException {
        out.close();
    }
}
