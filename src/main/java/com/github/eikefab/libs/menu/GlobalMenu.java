package com.github.eikefab.libs.menu;

import com.google.common.collect.ImmutableMap;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class GlobalMenu extends Menu {

    private Inventory inventory;
    private final MenuContext context;


    public GlobalMenu(String title, int size) {
        super(title, size);

        this.context = new MenuContext(this, ImmutableMap.of());
    }

    public MenuContext getContext() {
        return context;
    }

    protected void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    protected abstract void build(MenuContext context);

    public void update(MenuContext context) {
        if (inventory == null) setInventory(create(context));

        build(context);
        context.fill(inventory);
    }

    public void open(Player player, boolean update) {
        if (inventory == null || update) update(context);

        player.openInventory(inventory);
    }

    @Override
    public void open(Player player, Map<String, Object> data) {
        open(player, false);
    }

    @Override
    public void open(Player player) {
        open(player, false);
    }

    @Override
    protected void build(Player player, MenuContext context) {
        build(context);
    }

}
