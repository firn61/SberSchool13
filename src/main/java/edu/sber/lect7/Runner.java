package edu.sber.lect7;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Runner {
    private static final String PLUGIN_PATH = "pluginRootDirectory";
    private static List<Plugin> plugins = new ArrayList<>();

    public static void main(String[] args) {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        PluginManager pluginManager = new PluginManager(PLUGIN_PATH);
        Map<String, List<String>> pluginsNames = pluginManager.getAvaiblePlugins();
        for (Map.Entry<String, List<String>> el : pluginsNames.entrySet()) {
            for (String plugin : el.getValue()) {
                try {
                    Plugin pluginClass = pluginManager.load(el.getKey(), plugin);
                    if(pluginClass != null){
                        plugins.add(pluginClass);

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
        for (Plugin plugin : plugins) {
            if (plugin != null) {
                plugin.doUsefull();
            }
        }
    }
}
