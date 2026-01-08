package com.matt.autoannounce;

import net.minecraft.server.MinecraftServer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;


public class AutoAnnounceEvents {

    public int SecondsPassed = 0;

    @SubscribeEvent
    public void onServerTick(ServerTickEvent.Post event) {
        MinecraftServer server = event.getServer();
        AutoAnnounce.TicksPassed++;
        AutoAnnounce.AnnounceIfReqd(server);
    }
}
