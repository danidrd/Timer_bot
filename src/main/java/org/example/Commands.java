package org.example;


import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;


public class Commands extends ListenerAdapter {




    public void onMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        if(args[0].equalsIgnoreCase(TimerMain.prefix + "info")) {
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("TimerPotion Bot Information");
            info.setDescription("Usage : -setTimer for set a timer which lasts 10 minutes ");
            info.setColor(0xfc7f03);
            info.setFooter("Created by Giorgio6 and geoteo(ThreadScurreggione)", event.getMember().getUser().getAvatarUrl());

            event.getChannel().sendMessageEmbeds(info.build()).queue();
            info.clear();
        }else if(args[0].equalsIgnoreCase(TimerMain.prefix + "setTimer")) {

            final MessageChannelUnion channel = event.getChannel();
            final Member self = event.getMember();
            final GuildVoiceState selfVoiceState = self.getVoiceState();

            //if(selfVoiceState.inAudioChannel()) {
            //channel.sendMessage("Already in a voice channel").queue();
            //return ;
            //}

            final Member member = event.getMember();
            final GuildVoiceState memberVoiceState = member.getVoiceState();

            if(!memberVoiceState.inAudioChannel()) {
                channel.sendMessage("You need to be in a voice channel for this command to work").queue();
                return;
            }

            final AudioManager audioManager = event.getGuild().getAudioManager();
            final AudioChannel memberChannel = memberVoiceState.getChannel();

            audioManager.openAudioConnection(memberChannel);
            channel.sendMessageFormat("Connecting to %s", memberChannel.getName()).queue();

            AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
            AudioSourceManagers.registerRemoteSources(playerManager);

            AudioPlayer player = playerManager.createPlayer();

            //long startTime = System.currentTimeMillis();
            //long elapsedTime = 0L;
            //ScheduledExecutorService scheduledExecutorService = TimerMain.jda.getGatewayPool();

           /* while(elapsedTime < 600000) {
                elapsedTime = (new Date()).getTime() - startTime;
            }*/

            Thread t = new Thread(new ThreadScorreggione(channel));
            t.start();



            //audioManager.closeAudioConnection();






        }
    }
}

