package com.github.eikefab.libs.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public abstract class PageMenu extends Menu {

    private static final Map<Integer, Integer> ROW_DICT = new HashMap<>();

    static {
        ROW_DICT.put(1, 17);
        ROW_DICT.put(2, 25);
        ROW_DICT.put(3, 34);
        ROW_DICT.put(4, 44);
    }

    private final int lines;
    private final int targetSlot;

    private int currentPage = 1;

    public PageMenu(String title, int size, int rows) {
        super(title, size);

        if (rows < 0 || rows > 4) throw new IllegalArgumentException("Rows must be higher than 0 and less than 5.");

        this.lines = rows * 7;
        this.targetSlot = ROW_DICT.get(rows);
    }

    protected int getCurrentPage() {
        return currentPage;
    }

    protected abstract int itemsCount();

    protected int lastPage() {
        return (int) Math.ceil(itemsCount() / (double) lines);
    }

    protected void nextPage() {
        if (!hasNextPage()) return;

        currentPage++;
    }

    protected void previousPage() {
        if (isInitialPage()) return;

        currentPage--;
    }

    protected boolean isInitialPage() {
        return currentPage == 1;
    }

    protected boolean isLastPage() {
        return currentPage + 1 > lastPage();
    }

    protected boolean hasNextPage() {
        return !isLastPage();
    }

    protected abstract void build(Player player, PageMenuContext context, int currentPage);

    @Override
    public void open(Player player, Map<String, Object> data) {
        final PageMenuContext context = new PageMenuContext(this, lastPage(), data);
        final Inventory inventory = create(context);

        build(player, context, currentPage);

        if (!isInitialPage()) {
            previousPageItem(player, context);
        }

        if (!isLastPage()) {
            nextPageItem(player, context);
        }

        context.fill(inventory);
        player.openInventory(inventory);
    }

    @Override
    protected void build(Player player, MenuContext context) {
        build(player, (PageMenuContext) context, currentPage);
    }

    protected abstract void nextPageItem(Player player, PageMenuContext context);
    protected abstract void previousPageItem(Player player, PageMenuContext context);

    protected int currentStart() {
        return Math.min((currentPage - 1) * lines, itemsCount() - 1);
    }

    protected int currentEnd() {
        return Math.min((currentPage * lines), itemsCount() - 1);
    }

    protected <T> void fillInventory(List<T> content, PageMenuContext context, Function<T, MenuItem> function) {
        final int start = currentStart();
        final int end = currentEnd();

        final Iterator<T> iterator = content.subList(start, end).iterator();

        for (int slot = 10; slot <= targetSlot && iterator.hasNext(); slot++) {
            if (isBorder(slot)) continue;

            final T item = iterator.next();

            context.add(slot, function.apply(item));
        }
    }

}
