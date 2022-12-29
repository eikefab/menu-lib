package com.github.eikefab.libs.menu;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

class MenuHolder implements InventoryHolder {

    private final MenuContext context;

    public MenuHolder(MenuContext context) {
        this.context = context;
    }

    public MenuContext getContext() {
        return context;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }

}
