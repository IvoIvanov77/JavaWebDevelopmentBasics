package javache.io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Writer {

    private Writer() {
    }

    public static void write(byte[] data, OutputStream outputStream) throws IOException {
        DataOutputStream bufer = new DataOutputStream(outputStream);
        bufer.write(data);
    }
}
