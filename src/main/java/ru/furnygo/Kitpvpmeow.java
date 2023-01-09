package ru.furnygo;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import static ru.furnygo.Main.*;

public class Kitpvpmeow extends CommandBase {
    String settingsInfo = "Этот файл используется для хранения настроек мода, изменяемых через команды.\nАвтосообщения менять тут - .minecraft/config/KitPvPMeow.txt\n";

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public String getCommandName() {
        return "kitpvpmeow";
    }

    public void send(String str) {
        if (Minecraft.getMinecraft().thePlayer != null) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(str));
        }
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "Настройки мода";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length > 0) {
            switch (args[0].trim()) {
                case "regen":
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
                    FileManager.initFile("config/KitPvPMeow.txt");
                    FileManager.writeToFile(str, "config/KitPvPMeow.txt", false);
                    send("Готово!");
                    break;
                case "update":
                    FileManager.readFile("config/KitPvPMeow.txt");
                    send("Готово!");
                    break;
                case "show":
                    String readFile = FileManager.readFile("config/KitPvPMeow.txt");
                    send(readFile.replace("Ξ︈Ξ︇Ξ︆Ξ", "§9Ξ︈Ξ︇Ξ︆Ξ§r"));
                    break;
                case "disable":
                    switch (args[1].trim()) {
                        case "kills":
                            String str2 = settingsInfo + "-=-0-=-" + deathMessagesOn + "-=-" + joinMessagesOn + stats;
                            FileManager.writeToFile(str2, "config/KitPvPMeowValues.txt", false);
                            break;
                        case "deaths":
                            String str3 = settingsInfo + "-=-" + killMessagesOn + "-=-0-=-" + joinMessagesOn + stats;
                            FileManager.writeToFile(str3, "config/KitPvPMeowValues.txt", false);
                            break;
                        case "join":
                            String str4 = settingsInfo + "-=-" + killMessagesOn + "-=-" + deathMessagesOn + "-=-0" + stats;
                            FileManager.writeToFile(str4, "config/KitPvPMeowValues.txt", false);
                            break;
                        case "all":
                            String str5 = settingsInfo + "-=-0-=-0-=-0" + stats;
                            FileManager.writeToFile(str5, "config/KitPvPMeowValues.txt", false);
                            break;
                    }
                    killMessagesOn = Integer.parseInt(FileManager.readFile("config/KitPvPMeowValues.txt").split("-=-")[1]);
                    deathMessagesOn = Integer.parseInt(FileManager.readFile("config/KitPvPMeowValues.txt").split("-=-")[2]);
                    joinMessagesOn = Integer.parseInt(FileManager.readFile("config/KitPvPMeowValues.txt").split("-=-")[3].replace("\n", ""));
                    send("Готово!");
                    break;
                case "enable":
                    switch (args[1].trim()) {
                        case "kills":
                            String str2 = settingsInfo + "-=-1-=-" + deathMessagesOn + "-=-" + joinMessagesOn + stats;
                            FileManager.writeToFile(str2, "config/KitPvPMeowValues.txt", false);
                            break;
                        case "deaths":
                            String str3 = settingsInfo + "-=-" + killMessagesOn + "-=-1-=-" + joinMessagesOn + stats;
                            FileManager.writeToFile(str3, "config/KitPvPMeowValues.txt", false);
                            break;
                        case "join":
                            String str4 = settingsInfo + "-=-" + killMessagesOn + "-=-" + deathMessagesOn + "-=-1" + stats;
                            FileManager.writeToFile(str4, "config/KitPvPMeowValues.txt", false);
                            break;
                        case "all":
                            String str5 = settingsInfo + "-=-1-=-1-=-1" + stats;
                            FileManager.writeToFile(str5, "config/KitPvPMeowValues.txt", false);
                            break;
                    }
                    killMessagesOn = Integer.parseInt(FileManager.readFile("config/KitPvPMeowValues.txt").split("-=-")[1]);
                    deathMessagesOn = Integer.parseInt(FileManager.readFile("config/KitPvPMeowValues.txt").split("-=-")[2]);
                    joinMessagesOn = Integer.parseInt(FileManager.readFile("config/KitPvPMeowValues.txt").split("-=-")[3].replace("\n", ""));
                    send("Готово!");
                    break;
                case "stats":
                    switch (args[1].trim()) {
                        case "on":
                            String str2 = settingsInfo + "-=-"+ killMessagesOn +"-=-" + deathMessagesOn + "-=-" + joinMessagesOn + "-=-1";
                            FileManager.writeToFile(str2, "config/KitPvPMeowValues.txt", false);
                            break;
                        case "off":
                            String str3 = settingsInfo + "-=-"+ killMessagesOn +"-=-" + deathMessagesOn + "-=-" + joinMessagesOn + "-=-0";
                            FileManager.writeToFile(str3, "config/KitPvPMeowValues.txt", false);
                            break;
                    }
                    stats = Integer.parseInt(FileManager.readFile("config/KitPvPMeowValues.txt").split("-=-")[1]);
                    send("Готово!");
                    break;
            }
        } else {
            send("/kitpvpmeow regen - Сбросить сообщения");
            send("/kitpvpmeow update - Обновить сообщения");
            send("/kitpvpmeow show - Посмотреть сообщения");
            send("/kitpvpmeow disable kills/deaths/join/all - Отключить сообщения чего-то");
            send("/kitpvpmeow enable kills/deaths/join/all - Включить сообщения чего-то");
            send("/kitpvpmeow stats on/off - Отключить/Включить участие в составлении статистических данных об использовании мода");
        }
    }
}
