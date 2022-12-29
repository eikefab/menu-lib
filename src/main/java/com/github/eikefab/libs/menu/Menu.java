package com.github.eikefab.libs.menu;

import com.google.common.collect.ImmutableMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Map;

public abstract class Menu {

    private final String title;
    private final int size;

    public Menu(String title, int size) {
        this.title = title;
        this.size = size;

        if (size % 9 != 0) {
            throw new IllegalArgumentException("Size must be a multiple of 9.");
        }
    }

    protected Inventory create(MenuContext context) {
        return Bukkit.createInventory(context.getHolder(), size, title);
    }

    protected abstract void build(Player player, MenuContext context);

    protected void fillBorders(MenuItem item, MenuContext context) {
        for (int slot = 0; slot < size; slot++) {
            if (slot >= 10 && slot <= 43 && (!isBorder(slot))) continue;

            context.add(slot, item);
        }
    }

    protected boolean isBorder(int slot) {
        return slot % 9 == 0 || (slot + 1) % 9 == 0;
    }

    public void open(Player player, Map<String, Object> data) {
        final MenuContext context = new MenuContext(this, data);
        final Inventory inventory = create(context);

        build(player, context);

        context.fill(inventory);
        player.openInventory(inventory);
    }

    public void open(Player player) {
        open(player, ImmutableMap.of());
    }

}
