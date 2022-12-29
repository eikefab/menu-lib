package com.github.eikefab.libs.menu;

import com.github.eikefab.libs.menu.utils.ItemBuilder;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

public class MenuContext {

    private final Menu menu;
    private final Map<Integer, MenuItem> items;
    private final Map<String, Object> data;

    protected MenuContext(Menu menu, Map<String, Object> data) {
        this.items = new HashMap<>();
        this.data = data;
        this.menu = menu;
    }

    protected MenuHolder getHolder() {
        return new MenuHolder(this);
    }

    protected void fill(Inventory inventory) {
        for (Entry<Integer, MenuItem> entry : items.entrySet()) {
            final int slot = entry.getKey();
            final MenuItem item = entry.getValue();

            inventory.setItem(slot, item.getItemStack());
        }
    }

    protected Consumer<InventoryClickEvent> getItemConsumer(int itemSlot) {
        final MenuItem menuItem = items.getOrDefault(itemSlot, null);

        if (menuItem == null) return null;
        else return menuItem.getClickConsumer();
    }

    public MenuContext add(int slot, MenuItem item) {
        items.put(slot, item);

        return this;
    }

    public MenuContext add(int slot, ItemStack itemStack) {
        return add(slot, new MenuItem(itemStack));
    }

    public MenuContext add(int slot, ItemBuilder itemBuilder) {
        return add(slot, new MenuItem(itemBuilder));
    }

    public Object get(String key) {
        return data.getOrDefault(key, null);
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Map<Integer, MenuItem> getItems() {
        return items;
    }

}
