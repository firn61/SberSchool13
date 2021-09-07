package edu.sber.lect7;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PluginManager {
    private final String pluginRootDirectory;
    private ClassLoader classLoader;
    private final String packagePath;
    private String pathPath;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
        packagePath = this.getClass().getPackageName();
        //Path currentRelativePath = Paths.get("");
        pathPath = Paths.get("").toAbsolutePath().toString() + "/target/classes/"
                + packagePath.replace(".", "/") + "/" + pluginRootDirectory;
    }

    /**
     *
     * @param pluginName
     * @param pluginClassName
     * @return Returns null, when cannot initialize class
     * @throws MalformedURLException
     */
    public Plugin load(String pluginName, String pluginClassName) throws MalformedURLException {
        File filePath = new File(pathPath + "/" + pluginName);
        classLoader = new URLClassLoader(new URL[]{filePath.toURI().toURL()});
        try {
            String className = packagePath + "." + pluginRootDirectory + "." + pluginName + "." + pluginClassName;
            return (Plugin) Class.forName(className, true, classLoader).getConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } catch (NoSuchMethodException e) {
            System.out.println("No valid constructor");
        } catch (IllegalAccessException e) {
            System.out.println("Access modifier error");
        } catch (InvocationTargetException | InstantiationException e) {
            System.out.println("Cannot invoke instance");
        }
        return null;
    }

    public Map<String, List<String>> getAvaiblePlugins() {
        Map<String, List<String>> pluginMap = new HashMap<>();
        File targetFolder = new File(pathPath);
        File[] pluginFolders = targetFolder.listFiles(file -> file.isDirectory());
        for (File folder : pluginFolders) {
            File[] pluginClasses = folder.listFiles(file -> file.getName().endsWith(".class"));
            if (pluginClasses.length > 0) {
                List<String> list = new ArrayList<>();
                for (File pluginClass : pluginClasses) {
                    list.add(pluginClass.getName().replaceFirst("[.][^.]+$", ""));
                }
                pluginMap.put(folder.getName(), list);
            }
        }
        return pluginMap;
    }

}