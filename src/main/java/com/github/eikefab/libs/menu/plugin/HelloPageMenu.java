package com.github.eikefab.libs.menu.plugin;

import com.github.eikefab.libs.menu.MenuItem;
import com.github.eikefab.libs.menu.PageMenu;
import com.github.eikefab.libs.menu.PageMenuContext;
import com.github.eikefab.libs.menu.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HelloPageMenu extends PageMenu {

    private final List<String> content;

    public HelloPageMenu() {
        super("Page Menu", 6 * 9, 4);

        this.content = getContent();
    }

    @Override
    protected int itemsCount() {
        return content.size();
    }

    @Override
    protected void build(Player player, PageMenuContext context, int currentPage) {
        fillInventory(content, context, (name) -> new MenuItem(ItemBuilder.of(Material.BOOK).name(name)));
    }

    private List<String> getContent() {
        final List<String> content = new ArrayList<>();

        for (int i = 0; i < 70; i++) {
            content.add("&eItem #" + (i + 1));
        }

        return content;
    }

    @Override
    protected void nextPageItem(Player player, PageMenuContext context) {
        context.add(
                isInitialPage() ? 49 : 53,
                new MenuItem(
                        ItemBuilder.of(Material.ARROW)
                                .name("&aNext page")
                ).click((event) -> {
                    nextPage();

                    open(player, context.getData());
                })
        );
    }

    @Override
    protected void previousPageItem(Player player, PageMenuContext context) {
        context.add(
                hasNextPage() ? 45 : 49,
                new MenuItem(
                        ItemBuilder.of(Material.ARROW)
                                .name("&aPrevious page")
                ).click((event) -> {
                    previousPage();

                    open(player, context.getData());
                })
        );
    }

}
