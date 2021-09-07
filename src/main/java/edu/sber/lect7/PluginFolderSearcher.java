package edu.sber.lect7;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PluginFolderSearcher {

    private final String pluginFolder;

    PluginFolderSearcher(String pluginFolder) {
        this.pluginFolder = pluginFolder;
    }

    private FileFilter folderFileFilter = file -> file.isDirectory();

    private FileFilter classFileFilter = file -> file.getName().endsWith(".class");

    public Map<String, List<String>> getAvaiblePlugins() {
        Map<String, List<String>> pluginMap = new HashMap<>();
        File targetFolder = new File("target" + "\\" + "classes" + "\\" + PluginManager.class.getPackageName().replace(".", "\\") + "\\" + pluginFolder);
        File[] pluginFolders = targetFolder.listFiles(folderFileFilter);
        for (File folder : pluginFolders) {
            File[] pluginClasses = folder.listFiles(classFileFilter);
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
