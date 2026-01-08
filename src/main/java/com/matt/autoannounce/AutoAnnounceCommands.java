package com.matt.autoannounce;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import java.io.File;

public class AutoAnnounceCommands {

    public static void onRegisterCommands(RegisterCommandsEvent event ) {
        register( event.getDispatcher());
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher ) {
        dispatcher.register(
                Commands.literal("autoannounce")
                        .then( Commands.literal( "author" )
                                .executes( ctx -> showAuthor(ctx.getSource()))
                        )
                        .then( Commands.argument( "reload", EntityArgument.player() )
                                .executes( ctx -> reloadConfig( ctx.getSource() ) )
                        )

                        .executes( ctx -> showAuthor( ctx.getSource() )
                        )
        );
    }


    private static int reloadConfig( CommandSourceStack source ) {
        File configDir = FMLPaths.CONFIGDIR.get().toFile();
        AutoAnnounce.ModConfig = AutoAnnounceConfig.load( configDir );
        source.sendSuccess( () -> Component.literal("[AutoAnnouncer]: Config reloaded"), false );

        return 1;
    }

    private static int showAuthor( CommandSourceStack source ) {
        source.sendSuccess(() -> Component.literal( "[AutoAnnouncer]: Mod Author: ii_Matt" ), false );
        return 1;
    }



}

