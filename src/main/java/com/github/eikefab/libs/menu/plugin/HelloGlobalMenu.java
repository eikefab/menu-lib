package com.github.eikefab.libs.menu.plugin;

import com.github.eikefab.libs.menu.GlobalMenu;
import com.github.eikefab.libs.menu.MenuContext;
import com.github.eikefab.libs.menu.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

public class HelloGlobalMenu extends GlobalMenu {

    private final JavaPlugin plugin;
    private boolean firstTime = true;

    public HelloGlobalMenu(JavaPlugin plugin) {
        super("Global Menu", 27);

        this.plugin = plugin;
    }

    @Override
    protected void build(MenuContext context) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(ZoneId.of("America/Recife")));

        final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        context.add(
                13,
                ItemBuilder.of(Material.PAPER)
                        .name("&eCurrent time")
                        .lore(
                                "&7Day: &f" + calendar.get(Calendar.DAY_OF_MONTH),
                                "&7Month: &f" + calendar.get(Calendar.MONTH),
                                "&7Year: &f" + calendar.get(Calendar.YEAR),
                                "&7Time: &f" + dateFormat.format(calendar.getTimeInMillis())
                        )
        );

        if (firstTime) {
            new BukkitRunnable() {

                @Override
                public void run() {
                    update(context);
                }

            }.runTaskTimer(plugin, 0, 0);
        }

        firstTime = false;
    }

}
