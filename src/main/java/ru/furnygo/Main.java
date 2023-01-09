package ru.furnygo;


import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main {
    public static final String MODID = "kitpvpmeow";
    public static final String VERSION = "1.0";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static int killMessagesOn;
    public static int deathMessagesOn;
    public static int joinMessagesOn;
    public static boolean joinServer = true;
    public static int stats;
    public static String admin1;
    public static String admin2;
    public static String krytoi1;
    public static String customPrefix1;
    public static String custom1;
    public static String customPrefix2;
    public static String custom2;
    public static String customPrefix3;
    public static String custom3;

    public static String getText(final String url) throws Exception {
        final URL website = new URL(url);
        final URLConnection connection = website.openConnection();
        final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        final StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new Kitpvpmeow());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER.debug("KitPvPMeow enabled!");
        MinecraftForge.EVENT_BUS.register(new ChatListener());
        MinecraftForge.EVENT_BUS.register(new DisconnectEvent());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        FileManager.readFile("config/KitPvPMeow.txt");
        FileManager.readFile("config/KitPvPMeowValues.txt");
        java.io.File file = new java.io.File("config/KitPvPMeow.txt");
        if (file.length() == 0) {
            String str = "Файл для автосообщений мода, в скобочках написаны \n" +
                    "плейсхолдеры, которые заменяются на значения.\n" +
                    "Версия конфига - 1.0\n" +
                    "\n" +
                    "\n" +
                    "Ξ︈Ξ︇Ξ︆Ξ После убийства (%NICK% %COINS% %EXP%) (монеты и опыт только для кп2) Ξ︈Ξ︇Ξ︆Ξ\n" +
                    "Убил лоха и получил %COINS% монет и %EXP% опыта EZ\n" +
                    "Во ты нуб конечно %NICK%, слил тебя на изи\n" +
                    "%NICK%, тебе нужно больше тренироваться!\n" +
                    "Дружок, тебе надо тренироваться\n" +
                    "Малыш, тебе надо тренироваться\n" +
                    "%NICK%, ты не няшка <3\n" +
                    "%NICK%, последнее предупреждение!\n" +
                    "%NICK%, попробуй во время пвп нажимать на мышку\n" +
                    "%NICK%, суть игры не умирать, а убивать\n" +
                    "Спасибо, + килл в статистику\n" +
                    "Малыш, больше так не делай)\n" +
                    "Я просто бог в этой игре!\n" +
                    "%NICK%, просто я тренируюсь, пока все отдыхают\n" +
                    "Я сейчас в очень жосткой форме!\n" +
                    "Ой-ой-ой, не повезло тебе, %NICK%\n" +
                    "%NICK% езочка иди поплачь))\n" +
                    "Это нормально, %NICK%, нужно уметь принимать поражение\n" +
                    "%NICK%, потренируйся и приходи в следующий раз!\n" +
                    "Не расстраивайся, %NICK%, в следующий раз получится!\n" +
                    "Не расстраивайся, %NICK%, тебе повезет в следующий раз!\n" +
                    "Не грусти, %NICK%, потренируйся немного и все получится!\n" +
                    "Не грусти, %NICK%, в следующий раз все получится!\n" +
                    "\n" +
                    "Ξ︈Ξ︇Ξ︆Ξ После смерти (%NICK%) Ξ︈Ξ︇Ξ︆Ξ\n" +
                    "%NICK%, тебе просто повезло)\n" +
                    "%NICK%, на лакичах чисто\n" +
                    "%NICK%, пошёл нафиг читер\n" +
                    "Я просто поддался, %NICK%\n" +
                    "%NICK% не жди пощады теперь >:(\n" +
                    "Тебе просто повезло, %NICK%\n" +
                    "%NICK%, с читами норм?\n" +
                    "%NICK% у меня залагало))\n" +
                    "Готовь попку, %NICK%\n" +
                    "Ну, %NICK%, ты доигрался\n" +
                    "\n" +
                    "Ξ︈Ξ︇Ξ︆Ξ После входа (Только для кп2) Ξ︈Ξ︇Ξ︆Ξ\n" +
                    "Всем приветики!\n" +
                    "Я зашёл на сервер, всем встать!\n" +
                    "Заряжен и готов к бою!\n" +
                    "qq all\n" +
                    "Тихо зашёл на сервер...\n" +
                    "Здарова всем!\n" +
                    "Вечер в хату!";
            FileManager.writeToFile(str, "config/KitPvPMeow.txt", false);
        }
        java.io.File file2 = new java.io.File("config/KitPvPMeowValues.txt");
        if (file2.length() == 0) {
            String str = "Этот файл используется для хранения настроек мода, изменяемых через команды.\n" +
                    "Автосообщения менять тут - .minecraft/config/KitPvPMeow.txt\n" +
                    "Версия конфига - 1.0\n" +
                    "-=-1-=-1-=-1-=-1";
            FileManager.writeToFile(str, "config/KitPvPMeowValues.txt", false);
        }
        killMessagesOn = Integer.parseInt(FileManager.readFile("config/KitPvPMeowValues.txt").split("-=-")[1]);
        deathMessagesOn = Integer.parseInt(FileManager.readFile("config/KitPvPMeowValues.txt").split("-=-")[2]);
        joinMessagesOn = Integer.parseInt(FileManager.readFile("config/KitPvPMeowValues.txt").split("-=-")[3]);
        stats = Integer.parseInt(FileManager.readFile("config/KitPvPMeowValues.txt").split("-=-")[4].replace("\n", ""));
        String[] cheliki;
        try {
            cheliki = getText("url").split("ENDENDEND")[0].trim().split("STARTSTARTSTART")[1].split("-=-");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        admin1 = cheliki[0];
        admin2 = cheliki[1];
        krytoi1 = cheliki[2];
        customPrefix1 = cheliki[3];
        custom1 = cheliki[4];
        customPrefix2 = cheliki[5];
        custom2 = cheliki[6];
        customPrefix3 = cheliki[7];
        custom3 = cheliki[8];
    }

}
