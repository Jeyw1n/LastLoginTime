package vendek.delastlogintime;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LastLoginTime extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
    }

    private void saveData(String path, String value) {
        getConfig().set(path, value);
        saveConfig();
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // UID для сохранения
        String playerUID = player.getUniqueId().toString();

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date dateNow = new Date();
        // Дата для сохранения
        String joinDate = dateFormat.format(dateNow);
        // Старая дата входа (прошлый вход)
        String oldJoinDate = getConfig().getString(playerUID + ".join-date", "сейчас");

        // Сохраняем данные
        saveData(playerUID + ".join-date", joinDate);

        player.sendMessage( player.getName() + ChatColor.YELLOW + ", добро пожаловать!\n" + "Последний вход был: " + ChatColor.GOLD + oldJoinDate);
        event.setJoinMessage(null);
    }
}
