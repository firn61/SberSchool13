package edu.sber.lect7;

import edu.sber.lect7.pluginRootDirectory.CustomClassLoader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
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
        pathPath = Paths.get("").toAbsolutePath().toString() + "\\" + pluginRootDirectory;
    }

    /**
     * @param pluginName
     * @param pluginClassName
     * @return Returns null, when cannot initialize class
     * @throws MalformedURLException
     */
    public Plugin load(String pluginName, String pluginClassName) throws MalformedURLException {
        File filePath = new File(pathPath + "\\" + pluginName);
        classLoader = new CustomClassLoader(new URL[]{filePath.toURI().toURL()}, false);
        try {
            Plugin p = (Plugin) classLoader.loadClass(pluginClassName).getConstructor().newInstance();
            return p;
        } catch (ClassNotFoundException e) {
            //there is Plugin.class???
        } catch (NoSuchMethodException e) {
            //seems, that class is not plugin????
        } catch (IllegalAccessException e) {
            System.out.println("Access modifier error");
        } catch (InvocationTargetException | InstantiationException e) {
            System.out.println("Cannot invoke instance");
        } catch (NullPointerException e) {
            System.out.println("NPE");
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