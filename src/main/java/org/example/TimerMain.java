package org.example;

import java.util.EnumSet;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class TimerMain {

    public static JDA jda;
    public static String prefix = "-";

    public static void main(String[] args ) throws LoginException {
        jda = JDABuilder.createDefault(System.getenv().get("TOKEN"))
                .setAutoReconnect(true)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .disableIntents(GatewayIntent.GUILD_PRESENCES)
                .disableCache(EnumSet.of(
                        CacheFlag.CLIENT_STATUS,
                        CacheFlag.ACTIVITY,
                        CacheFlag.EMOJI
                ))
                .enableCache(CacheFlag.VOICE_STATE)
                .addEventListeners(new Commands())
                .build();
        jda.getPresence().setActivity(Activity.playing("type -info to display infos"));
        jda.getPresence().setStatus(OnlineStatus.IDLE);



    }
}