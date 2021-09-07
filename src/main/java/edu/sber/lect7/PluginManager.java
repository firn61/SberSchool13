package edu.sber.lect7;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;

public class PluginManager {
    private final String pluginRootDirectory;
    private ClassLoader classLoader;
    private final String packagePath;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
        packagePath = this.getClass().getPackageName();
    }

    /**
     *
     * @param pluginName
     * @param pluginClassName
     * @return Returns null, when cannot initialize class
     * @throws MalformedURLException
     */
    public Plugin load(String pluginName, String pluginClassName) throws MalformedURLException {
        File filePath = new File("target" + "\\" + "classes"  + "\\" + PluginManager.class.getPackageName().replace(".", "\\") + "\\"  + pluginName);
        classLoader = new URLClassLoader(new URL[]{filePath.toURI().toURL()});
        try {
            String className = packagePath + "." + pluginRootDirectory + "." + pluginName + "." + pluginClassName;
            Class pluginClass = Class.forName(className, true, classLoader);
            return (Plugin) pluginClass.getConstructor().newInstance();
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
}