package org.softuni.broccolina.util;

import org.softuni.broccolina.solet.BaseHttpSolet;
import org.softuni.broccolina.solet.HttpSolet;
import org.softuni.broccolina.solet.WebSolet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ApplicationLoadingService {

    private Map<String, HttpSolet> loadedApplications;

    private JarFileUnzipService jarFileUnzipService;

    public ApplicationLoadingService() {
        this.loadedApplications = new HashMap<>();
        this.jarFileUnzipService = new JarFileUnzipService();
    }

    private void loadHttpSolet(Class<?> httpSoletClass) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        if(!BaseHttpSolet.class.isAssignableFrom(httpSoletClass)){
            return;
        }
        BaseHttpSolet httpSolet =
                (BaseHttpSolet) httpSoletClass
                        .getDeclaredConstructor()
                        .newInstance();

        WebSolet webSoletAnnotation = httpSolet.getClass()
                .getAnnotation(WebSolet.class);

        this.loadedApplications.put(webSoletAnnotation.route(),httpSolet);

    }

    private void loadClass(File currentFile, String canonicalPath,
                           URLClassLoader urlClassLoader) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        if(currentFile.isDirectory()){
            for (File file : currentFile.listFiles()) {
                this.loadClass(file, file.getCanonicalPath(), urlClassLoader);
            }
        }else {
            if(!currentFile.getName().endsWith(".class")){
                return;
            }
            String className = currentFile
                    .getName()
                    .replace(".class", "")
                    .replace("/", ".");

            Class currentClassFile = urlClassLoader.loadClass(className);

            this.loadHttpSolet(currentClassFile);

        }
    }

    private void loadApplicationsClasses(String classesRootFolderPath) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        File classesRootDirectory = new File(classesRootFolderPath);

        if (!classesRootDirectory.exists() || !classesRootDirectory.isDirectory()) {
            return;
        }
        URL[] urls = new URL[]{new URL("file:/"
                + classesRootDirectory.getCanonicalPath()
                + File.separator)};

        URLClassLoader urlClassLoader = new URLClassLoader(urls);
        this.loadClass(classesRootDirectory, classesRootDirectory.getCanonicalPath(), urlClassLoader);
    }


    private void loadApplicationFromFolder(String applicationRootFolderPath) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String classesRootFolderPath = applicationRootFolderPath + "classes" + File.separator;
        String librariesRootFolderPath = applicationRootFolderPath + "lib" + File.separator;
        this.loadApplicationsClasses(classesRootFolderPath);
    }


    private boolean isJarFile(File file){
        return file.isFile() && file.getName().endsWith(".jar");
    }

    public Map<String,HttpSolet> loadApplications(String applicationsFolderPath)  {

        try {
            File applicationsFolder = new File(applicationsFolderPath);

            if(applicationsFolder.exists() && applicationsFolder.isDirectory()){
                List<File> jarFiles = Arrays.stream(applicationsFolder.listFiles())
                        .filter(this::isJarFile)
                        .collect(Collectors.toList());

                for (File jarFile : jarFiles) {
                    this.jarFileUnzipService.unzipJar(jarFile);
                    this.loadApplicationFromFolder(jarFile.getCanonicalPath()
                            .replace(".jar", File.separator));
                }

            }
            return this.loadedApplications;

        }catch (ClassNotFoundException | IOException
                | NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }
        return this.loadedApplications;
    }


}
