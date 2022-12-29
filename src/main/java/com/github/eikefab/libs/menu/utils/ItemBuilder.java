package com.github.eikefab.libs.menu.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class ItemBuilder {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Material type) {
        this(new ItemStack(type));
    }

    public ItemBuilder(Material type, int amount) {
        this(new ItemStack(type, amount));
    }

    public static ItemBuilder of(ItemStack itemStack) {
        return new ItemBuilder(itemStack);
    }

    public static ItemBuilder of(Material material) {
        return new ItemBuilder(material);
    }

    public static ItemBuilder of(Material material, int amount) {
        return new ItemBuilder(material, amount);
    }

    public ItemBuilder apply(Consumer<ItemMeta> itemMetaConsumer) {
        itemMetaConsumer.accept(itemMeta);

        return this;
    }

    public ItemBuilder name(String name) {
        return apply(itemMeta -> itemMeta.setDisplayName(applyColors(name)));
    }

    public ItemBuilder lore(List<String> lore) {
        return apply(itemMeta -> itemMeta.setLore(lore.stream().map(this::applyColors).collect(Collectors.toList())));
    }

    public ItemBuilder lore(String... lore) {
        return lore(Arrays.asList(lore));
    }

    public ItemBuilder flag(ItemFlag... flags) {
        return apply(itemMeta -> itemMeta.addItemFlags(flags));
    }

    public ItemBuilder enchant(Enchantment enchantment, int level) {
        return apply(itemMeta -> itemMeta.addEnchant(enchantment, level, true));
    }

    public ItemBuilder unbreakable() {
        return apply(itemMeta -> itemMeta.setUnbreakable(true));
    }

    public ItemStack getItemStack() {
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    private String applyColors(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
