package com.github.eikefab.libs.menu.plugin;

import com.github.eikefab.libs.menu.Menu;
import com.github.eikefab.libs.menu.MenuContext;
import com.github.eikefab.libs.menu.MenuItem;
import com.github.eikefab.libs.menu.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class HelloMenu extends Menu {

    public HelloMenu() throws IllegalArgumentException {
        super("Hello!", 27);
    }

    @Override
    protected void build(Player player, MenuContext context) {
        final int times = Integer.parseInt(context.get("times").toString());

        final MenuItem item = new MenuItem(
                ItemBuilder.of(Material.BOOK)
                .name("&e" + player.getName() + "'s hello")
                .lore("&7Click here to say hello on chat!")
        ).click((event) -> {
            player.chat("Hello everyone " + times + "!");
            player.closeInventory();
        });

        context.add(13, item);
    }

}
