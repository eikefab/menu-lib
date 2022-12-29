package com.github.eikefab.libs.menu;

import com.github.eikefab.libs.menu.utils.ItemBuilder;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class MenuItem {

    private final ItemStack itemStack;
    private Consumer<InventoryClickEvent> clickConsumer;

    public MenuItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public MenuItem(ItemBuilder itemBuilder) {
        this(itemBuilder.getItemStack());
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public MenuItem click(Consumer<InventoryClickEvent> clickConsumer) {
        this.clickConsumer = clickConsumer;

        return this;
    }

    protected Consumer<InventoryClickEvent> getClickConsumer() {
        return clickConsumer;
    }

}
