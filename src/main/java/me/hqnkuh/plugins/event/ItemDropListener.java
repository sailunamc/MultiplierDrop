package me.hqnkuh.plugins.event;

import me.hqnkuh.plugins.MultiplierDrop;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;

public class ItemDropListener implements Listener {

    @EventHandler
    public void onItemSpawn(ItemSpawnEvent event) {
        Item item = event.getEntity();

        if (item.getEntitySpawnReason() == CreatureSpawnEvent.SpawnReason.CUSTOM) return;

        World world = item.getWorld();
        Location location = item.getLocation();
        ItemStack itemStack = item.getItemStack();

        for (int i = 1; i < MultiplierDrop.getInstance().getMultiplier(); i++) {
            world.dropItem(location, itemStack);
        }
    }
}
