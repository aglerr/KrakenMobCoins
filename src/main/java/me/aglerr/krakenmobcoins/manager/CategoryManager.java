package me.aglerr.krakenmobcoins.manager;

import me.aglerr.krakenmobcoins.MobCoins;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.*;

public class CategoryManager {

    private final Map<String, FileConfiguration> categories = new HashMap<>();

    private final MobCoins plugin;
    public CategoryManager(final MobCoins plugin){
        this.plugin = plugin;
    }

    public void clearCategory(){
        categories.clear();
    }

    public void addCategory(String name, FileConfiguration config){
        categories.put(name, config);
    }

    public boolean isCategoryExist(String name){
        return categories.containsKey(name);
    }

    public FileConfiguration getCategory(String name){
        return categories.get(name);
    }

    public List<String> getCategoryList(){
        List<String> list = new ArrayList<>();
        for(String name : categories.keySet()){
            String[] split = name.replace(".", ";").split(";");
            list.add(split[0]);
        }

        return list;
    }

    public void loadCategory(){
        File[] files = new File(plugin.getDataFolder() + File.separator + "categories").listFiles();
        if(files.length > 0){
            for(File file : files){
                plugin.getLogger().info("Category Name: " + file.getName());
                addCategory(file.getName(), YamlConfiguration.loadConfiguration(file));
            }
        }

    }

}
