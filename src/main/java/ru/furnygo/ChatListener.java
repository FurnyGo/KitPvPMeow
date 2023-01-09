package ru.furnygo;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import static ru.furnygo.Main.*;

public class ChatListener {
    public void send(String str) {
        if (Minecraft.getMinecraft().thePlayer != null) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(str));
        } else System.out.println("govno");
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent e) {

        String msg = e.message.getUnformattedText();
        String msgFormat = e.message.getFormattedText();
        String messages = FileManager.readFile("config/KitPvPMeow.txt");
        String settings = FileManager.readFile("config/KitPvPMeowValues.txt");
        String[] messagesKill = messages.split("Ξ︈Ξ︇Ξ︆Ξ")[2].split("\n");
        String[] messagesDeath = messages.split("Ξ︈Ξ︇Ξ︆Ξ")[4].split("\n");
        String[] messagesJoin = messages.split("Ξ︈Ξ︇Ξ︆Ξ")[6].split("\n");
        String myName = Minecraft.getMinecraft().thePlayer.getGameProfile().getName();
        String serverr = Minecraft.getMinecraft().getCurrentServerData().serverIP;

        if (msg.contains(admin1)) {
            send(msgFormat.replace(admin1, "§f[§9Создатель мода§f] §c§l" + admin1 + "§f"));
            Minecraft.getMinecraft().thePlayer.playSound("random.orb", 1.0f, 1.0f);
            e.setCanceled(true);
        }
        if (msg.contains(admin2)){
            send(msgFormat.replace(admin2, "§f[§9Создатель мода§f] §c§l" + admin2 + "§r"));
            Minecraft.getMinecraft().thePlayer.playSound("random.orb", 1.0f, 1.0f);
            e.setCanceled(true);
        }
        if (msg.contains(krytoi1)) {
            send(msgFormat.replace(krytoi1, "§f[§9Помощник мода§f] §6" + krytoi1 + "§f"));
            e.setCanceled(true);
        }
        if (msg.contains(custom1)) {
            send(msgFormat.replace(custom1, customPrefix1 + custom1 + "§f"));
            e.setCanceled(true);
        }
        if (msg.contains(custom2)) {
            send(msgFormat.replace(custom2, customPrefix2 + custom2 + "§r"));
            e.setCanceled(true);
        }
        if (msg.contains(custom3)) {
            send(msgFormat.replace(custom3, customPrefix3 + custom3 + "§r"));
            e.setCanceled(true);
        }

        if (Main.joinServer) {
            send("§e§l* §fВы играете с модом KitPvPMeow §9(автор: FurnyGo)§r");
            send("§e§l* §fКонфиг тут - §9.minecraft/config/KitPvPMeow.txt§r");
            send("§e§l* §fВсе команды - §9/kitpvpmeow§r");
            if (!messages.split("Ξ︈Ξ︇Ξ︆Ξ")[0].contains("1.0") || !settings.split("-=-")[0].contains("1.0")) send("§c§l* §cВерсия конфига KitPvPMeow не соответствует версии мода, возможно вам нужно скачать новую версию или прописать /kitpvpmeow regen либо /kitpvpmeow update§r");
            if (Main.stats == 1) {
                // Просто для статистики
                Date date = new Date();
                DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                df.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
                DiscordWebhook webhook = new DiscordWebhook("url");
                webhook.addEmbed(new DiscordWebhook.EmbedObject()
                        .setTitle("KitPvPMeow 1.0")
                        .setDescription(myName + " joined " + serverr)
                        .setColor(Color.RED)
                        .setFooter(df.format(date), "https://tickettool.xyz/images/footer.png"));

                try {
                    webhook.execute();
                } catch (IOException err) {
                    err.printStackTrace();
                }
                Main.joinServer = false;
            }
        }
        if (msg.startsWith("Вы убили") && killMessagesOn == 1) {
            String nick = msg.split(" ")[2];
            String coins = msg.split(" ")[5];
            String exp = msg.split(" ")[8];
            Random rand = new Random();
            int random = rand.nextInt(messagesKill.length - 1);
            String m = messagesKill[random];
            if (m != null && !nick.equals(myName)) {
                if (m.equals("")) m = messagesKill[random];
                Minecraft.getMinecraft().thePlayer.sendChatMessage(m.replace("%NICK%", nick).replace("%COINS%", coins).replace("%EXP%", exp));
            }
        } else if (msg.startsWith("Вы были убиты") && deathMessagesOn == 1) {
            String nick = msg.split(" ")[3].replace("!", "");
            Random rand = new Random();
            int random = rand.nextInt(messagesDeath.length - 1);
            String m = messagesDeath[random];
            if (m != null && !nick.equals(myName)) {
                if (m.equals("")) m = messagesDeath[random];
                Minecraft.getMinecraft().thePlayer.sendChatMessage(m.replace("%NICK%", nick));
            }
        } else if (msg.startsWith("Вы присоединились к карте") && joinMessagesOn == 1) {
            Random rand = new Random();
            int random = rand.nextInt(messagesJoin.length - 1);
            String m = messagesJoin[random];
            if (m != null) {
                if (m.equals("")) m = messagesJoin[random];
                Minecraft.getMinecraft().thePlayer.sendChatMessage(m);
            }
        } else if (msg.startsWith("KitPvP")) {
            if (msg.contains(myName) && killMessagesOn == 1) {
                if (msg.contains("был разорван на кусочки игроком") || msg.contains("был убит игроком") || msg.contains("не смог противостоять оружию игрока")) {
                    String nick = msg.split(" ")[3];
                    System.out.println("Detect kill message");
                    Random rand = new Random();
                    int random = rand.nextInt(messagesKill.length - 1);
                    String m = messagesKill[random];
                    if (m != null && !nick.equals(myName)) {
                        if (m.equals("")) m = messagesKill[random];
                        Minecraft.getMinecraft().thePlayer.sendChatMessage(m.replace("%NICK%", nick).replace("%COINS%", "дофига").replace("%EXP%", "дофига"));
                    }
                } else if (msg.contains("не смог удержаться от убийства")) {
                    String nick = msg.split(" ")[8].replace("!", "");
                    Random rand = new Random();
                    int random = rand.nextInt(messagesKill.length - 1);
                    String m = messagesKill[random];
                    if (m != null && !nick.equals(myName)) {
                        if (m.equals("")) m = messagesKill[random];
                        Minecraft.getMinecraft().thePlayer.sendChatMessage(m.replace("%NICK%", nick).replace("%COINS%", "дофига").replace("%EXP%", "дофига"));
                    }
                } else if (msg.contains("помог игроку")) {
                    String nick = msg.split(" ")[5].replace("!", "");
                    Random rand = new Random();
                    int random = rand.nextInt(messagesKill.length - 1);
                    String m = messagesKill[random];
                    if (m != null && !nick.equals(myName)) {
                        if (m.equals("")) m = messagesKill[random];
                        Minecraft.getMinecraft().thePlayer.sendChatMessage(m.replace("%NICK%", nick).replace("%COINS%", "дофига").replace("%EXP%", "дофига"));
                    }
                }
            } else if (msg.contains("Вы были убиты игроком")) {
                String nick = msg.split(" ")[6].replace("!", "");
                Random rand = new Random();
                int random = rand.nextInt(messagesDeath.length - 1);
                String m = messagesDeath[random];
                if (m != null && !nick.equals(myName)) {
                    if (m.equals("")) m = messagesDeath[random];
                    Minecraft.getMinecraft().thePlayer.sendChatMessage(m.replace("%NICK%", nick));
                }
            }
        }
    }
}
