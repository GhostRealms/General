package com.rmarmorstein.general;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by River on 2/5/2015.
 */
public class General extends JavaPlugin {
    
    private ModLoader modLoader = null;
    
    @Override
    public void onEnable() {
        modLoader = new ModLoader(this);
        
    }
    
    @Override
    public void onDisable() {
        
        
    }
}
