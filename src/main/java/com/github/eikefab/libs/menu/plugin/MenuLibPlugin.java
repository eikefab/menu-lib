package com.github.eikefab.libs.menu.plugin;

import com.github.eikefab.libs.menu.MenuListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MenuLibPlugin extends JavaPlugin {

    private final HelloGlobalMenu globalMenu;

    public MenuLibPlugin() {
        this.globalMenu = new HelloGlobalMenu(this);
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getCommand("hellomenu").setExecutor(new HelloMenuCommand(globalMenu));
    }

    public HelloGlobalMenu getGlobalMenu() {
        return globalMenu;
    }

}
