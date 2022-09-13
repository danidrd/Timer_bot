package org.example;

import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;

public class ThreadScorreggione implements Runnable {

    private final MessageChannelUnion messageChannelUnion;
    private final ThreadMain threadMain;

    public ThreadScorreggione(MessageChannelUnion mcu, ThreadMain threadMain){
        this.threadMain = threadMain;
        this.messageChannelUnion = mcu;
    }

    @Override
    public void run() {

        synchronized (this){
            try{
                wait(600000);
                PlayerManager.getInstance()
                        .loadAndPlay(messageChannelUnion.asTextChannel(), "https://www.youtube.com/watch?v=VM1y7s4V4Cs");
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        synchronized (threadMain){
            threadMain.notify();
        }





    }
}
