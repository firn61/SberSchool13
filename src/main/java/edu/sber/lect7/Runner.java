package edu.sber.lect7;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Runner {
    private static final String PLUGIN_PATH = "pluginRootDirectory";
    private static List<Plugin> plugins = new ArrayList<>();

    public static void main(String[] args) {
        PluginFolderSearcher pluginFolderSearcher = new PluginFolderSearcher(PLUGIN_PATH);
        PluginManager pluginManager = new PluginManager(PLUGIN_PATH);
        Map<String, List<String>> pluginsNames = pluginFolderSearcher.getAvaiblePlugins();
        for (Map.Entry<String, List<String>> el : pluginsNames.entrySet()){
            for (String plugin: el.getValue()){
                try{
                    plugins.add(pluginManager.load(el.getKey(), plugin));
                }catch(MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
        for (Plugin plugin : plugins) {
            plugin.doUsefull();
        }
    }
}
