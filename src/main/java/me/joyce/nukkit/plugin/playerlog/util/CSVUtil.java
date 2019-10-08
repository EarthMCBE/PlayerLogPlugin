package me.joyce.nukkit.plugin.playerlog.util;

import me.joyce.nukkit.plugin.playerlog.core.PlayerLogPlugin;

import java.io.*;
import java.time.LocalDate;

public class CSVUtil {

    private static final String logFileDir = "plugins/PlayerLogPlugin/logs/";
    private static final Character SEPERATOR = ',';
    private static final Character NEW_LINE = '\n';

    private static String logFilePath;

    static {
        File dir = new File(logFileDir);
        if(dir.exists() == false) {
            dir.mkdirs();
        }
        LocalDate date = LocalDate.now();
        logFilePath = logFileDir + date.getYear() + "-" + date.getMonthValue() + ".csv";
        File file = new File(logFilePath);
        if(file.exists() == false) {
            try {
                file.createNewFile();
                writeln("时间", "类型", "ID", "UUID", "名称", "IP", "游戏模式", "世界ID", "x坐标", "y坐标", "z坐标", "血量", "时间截", "在线时间(秒)");
            } catch(IOException e) {
                e.printStackTrace();
                PlayerLogPlugin.getPlugin().getLogger().error(file.getAbsolutePath() + "文件创建失败 " + e.getMessage());
            }
        }
    }

    public static void writeln(Object ...objs) {
        if(objs == null || objs.length == 0) return;
        StringBuilder sb = new StringBuilder();
        for(Object obj : objs) {
            if(sb.length() > 0) {
                sb.append(SEPERATOR);
            }
            sb.append(obj);
        }
        sb.append(NEW_LINE);
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFilePath, true), "GBK"));
            writer.write(sb.toString());
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
            PlayerLogPlugin.getPlugin().getLogger().error(logFilePath + "文件写入异常 " + e.getMessage());
        }
    }


}
