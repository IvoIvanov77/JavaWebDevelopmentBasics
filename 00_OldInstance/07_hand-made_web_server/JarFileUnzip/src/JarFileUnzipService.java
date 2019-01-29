import java.io.*;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarFileUnzipService {


    private void deleteFolder(File folder){
        if (!folder.exists()){
            return;
        }
        for (File file : folder.listFiles()) {
            if (file.isDirectory()){
                deleteFolder(file);
            }
            file.delete();
        }
    }

    public void unzipJar(File jarFile) throws IOException {
        String rootCanonicalPath = jarFile.getCanonicalPath();

        JarFile fileAsJar = new JarFile(rootCanonicalPath);

        File unzippedFolder = new File(rootCanonicalPath.replace(".jar", ""));

        if(unzippedFolder.exists() && unzippedFolder.isDirectory()){
            deleteFolder(unzippedFolder);
        }
        unzippedFolder.mkdir();

        Enumeration<JarEntry> jarEntries = fileAsJar.entries();

        while (jarEntries.hasMoreElements()){
            JarEntry currentEntry = jarEntries.nextElement();

            String currentEntryCanonicalPath = unzippedFolder.getCanonicalPath()
                    + File.separator
                    + currentEntry.getRealName();

            File currentEntryAsFile = new File(currentEntryCanonicalPath);

            if(currentEntry.isDirectory()){
                System.out.println("is directory");
                currentEntryAsFile.mkdir();
                continue;
            }

            InputStream currentEntryInputStream = fileAsJar.getInputStream(currentEntry);
            OutputStream currentEntryOutputStream = new FileOutputStream(
                    currentEntryAsFile.getCanonicalPath()
            );

            while (currentEntryInputStream.available() > 0){
                currentEntryOutputStream.write(currentEntryInputStream.read());
            }

            currentEntryInputStream.close();
            currentEntryOutputStream.close();
        }
        fileAsJar.close();
    }
}
