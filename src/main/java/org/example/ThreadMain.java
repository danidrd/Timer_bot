package org.example;

import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;

public class ThreadMain implements Runnable{
    private final MessageChannelUnion messageChannelUnion;
    private  Integer i;

    public ThreadMain(MessageChannelUnion messageChannelUnion, Integer integer) {
        this.messageChannelUnion = messageChannelUnion;
        this.i = integer;
    }

    @Override
    public void run() {
        while(i > 10){
            this.i-= 10;
            synchronized (this){
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            Thread t = new Thread(new ThreadScorreggione(messageChannelUnion,this));
            t.start();
        }
    }
}
