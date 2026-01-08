package com.matt.autoannounce;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

import java.io.File;
import java.util.Objects;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(AutoAnnounce.MODID)
public class AutoAnnounce {
    public static final String MODID = "autoannounce";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static AutoAnnounceConfig ModConfig;
    public static int TicksPassed = 0;

    public AutoAnnounce(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        /* Load config from file */
        File configDir = FMLPaths.CONFIGDIR.get().toFile();
        ModConfig = AutoAnnounceConfig.load(configDir);

        /* Load chat commands */
        NeoForge.EVENT_BUS.addListener(AutoAnnounceCommands::onRegisterCommands);

        /* Load events class */
        NeoForge.EVENT_BUS.register(new AutoAnnounceEvents());
    }

    public static void AnnounceIfReqd(MinecraftServer server) {
        if( TicksPassed <= 0 || (TicksPassed % 20) != 0 ) {
            return;
        }

        long seconds = TicksPassed / 20;

        for( var Announcement : ModConfig.Announcements ) {
            if( seconds % Announcement.FrequencySeconds == 0 ) {
                String msg = String.format( "[AutoAnnouncer]: %s", Announcement.AnnouncementMsg );
                server.getPlayerList().broadcastSystemMessage(Component.literal(msg), false);
                LOGGER.info( msg );
            }
        }
    }
    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("AutoAnnounce mod loaded");
    }
}
