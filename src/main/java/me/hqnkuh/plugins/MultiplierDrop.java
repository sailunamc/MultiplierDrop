package me.hqnkuh.plugins;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import me.hqnkuh.plugins.command.MultiplierCommand;
import me.hqnkuh.plugins.event.ItemDropListener;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;

public class MultiplierDrop extends JavaPlugin {
    private static MultiplierDrop instance;
    private int multiplier;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        multiplier = getConfig().getInt("multiplier");
        getServer().getPluginManager().addPermission(new Permission("md.commands.multiplier", PermissionDefault.OP));
        getServer().getPluginManager().registerEvents(new ItemDropListener(), this);
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> commands.registrar().register(MultiplierCommand.create()));
    }

    public static MultiplierDrop getInstance() {
        return instance;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int value) {
        multiplier = value;
        getConfig().set("multiplier", multiplier);
        saveConfig();
    }
}
