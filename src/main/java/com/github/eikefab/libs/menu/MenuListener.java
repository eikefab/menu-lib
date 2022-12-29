package com.github.eikefab.libs.menu;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.function.Consumer;

public class MenuListener implements Listener {

    @EventHandler
    private void handle(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;

        final Inventory inventory = event.getInventory();
        final InventoryHolder inventoryHolder = inventory.getHolder();

        if (!(inventoryHolder instanceof MenuHolder)) return;

        event.setCancelled(true);

        final MenuHolder holder = (MenuHolder) inventoryHolder;
        final MenuContext context = holder.getContext();

        final int itemSlot = event.getSlot();

        final Consumer<InventoryClickEvent> consumer = context.getItemConsumer(itemSlot);

        if (consumer == null) return;

        consumer.accept(event);
    }

}
