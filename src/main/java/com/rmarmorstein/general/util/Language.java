package com.rmarmorstein.general.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by River on 2/6/2015.
 * Language interface* 
 */
public class Language {
    
    private final Plugin instance;
    private final File langFile;
    private FileConfiguration langConf; 
    private Map<String, String> keyLangMap = new HashMap<String, String>();
    
    public Language(Plugin general)  {
        instance = general;
        langFile = new File(general.getDataFolder(), "lang.yml");
        importDefaults();
    }
    
    private void reloadConf() {
        if(langConf == null) {
            YamlConfiguration.loadConfiguration(langFile);
        }
        try {
            Reader reader = new InputStreamReader(instance.getResource("lang.yml"), "UTF8");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(reader);
            langConf.setDefaults(config);
        } catch (IOException ex) {
            ex.printStackTrace();
            reloadConf();
        }
    }
    
    private void getDefaults() {
        langConf = YamlConfiguration.loadConfiguration(langFile);
        if(!langFile.exists()) {
            instance.saveResource("lang.yml", false);
        }
        reloadConf();
    }
    
    protected FileConfiguration getLangConf() {
        if(langConf == null) {
            reloadConf();
            return getLangConf();
        }
        return langConf;
    }
    
    protected void saveLangConf() {
        if(langConf == null) {
            return;
        }
        try {
            getLangConf().save(langFile);
        } catch (IOException ex) {
            ex.printStackTrace();;
        }
    }
    
    private void importDefaults() {
        //TODO import all file contents into the map with it's key and value
    }
    
    public String getLang(String key) {
        return keyLangMap.get(key);
    }
}