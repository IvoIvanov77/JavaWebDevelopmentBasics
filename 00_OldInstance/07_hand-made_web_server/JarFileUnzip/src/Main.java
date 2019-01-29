import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        File file = new File("ROOT.jar");
        System.out.println(file.exists());
        JarFileUnzipService jarFileUnzipService = new JarFileUnzipService();
        jarFileUnzipService.unzipJar(file);

    }
}
