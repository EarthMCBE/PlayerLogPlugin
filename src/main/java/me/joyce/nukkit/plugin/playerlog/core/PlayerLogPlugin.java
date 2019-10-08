package me.joyce.nukkit.plugin.playerlog.core;

import cn.nukkit.plugin.PluginBase;
import me.joyce.nukkit.plugin.playerlog.listener.LogListener;

public class PlayerLogPlugin extends PluginBase {

    private static PlayerLogPlugin plugin;

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new LogListener(), this);
        plugin = this;
        super.onEnable();
    }

    public static PlayerLogPlugin getPlugin() {
        return plugin;
    }
}
