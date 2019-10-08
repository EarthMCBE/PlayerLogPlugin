package me.joyce.nukkit.plugin.playerlog.core;

import cn.nukkit.Player;
import cn.nukkit.level.Position;
import me.joyce.nukkit.plugin.playerlog.util.CSVUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class PlayerLogger {

    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    private static HashMap<String, Long> onlineUsers = new HashMap<>();

    public static void logLoginEvent(Player player) {
        LocalDateTime nowTime = LocalDateTime.now();
        String dateTime = nowTime.format(format);
        long id = player.getId();
        String uuid = player.getUniqueId().toString();
        String name = player.getName();
        String ip = player.getAddress();
        int gamemode = player.getGamemode();
        Position position = player.getPosition();
        int levelId = position.getLevel().getId();
        double x = position.getX();
        double y = position.getY();
        double z = position.getZ();
        float health = player.getHealth();
        long time = System.currentTimeMillis();
        onlineUsers.put(uuid, time);
        CSVUtil.writeln(dateTime, "登入", id, uuid, name, ip, gamemode, levelId, x, y, z, health, time);
    }

    public static void logLeftEvent(Player player) {
        LocalDateTime nowTime = LocalDateTime.now();
        String dateTime = nowTime.format(format);
        long id = player.getId();
        String uuid = player.getUniqueId().toString();
        String name = player.getName();
        String ip = player.getAddress();
        int gamemode = player.getGamemode();
        Position position = player.getPosition();
        int levelId = position.getLevel().getId();
        double x = position.getX();
        double y = position.getY();
        double z = position.getZ();
        float health = player.getHealth();
        long time = System.currentTimeMillis();
        long onlineTime = 0;
        Long loginTime = onlineUsers.get(uuid);
        if( loginTime != null ) {
            onlineTime = ( time - loginTime ) / 1000;
            onlineUsers.remove(uuid);
        }
        CSVUtil.writeln(dateTime, "登出", id, uuid, name, ip, gamemode, levelId, x, y, z, health, time, onlineTime);
    }
}
