package ru.astemir.keepinventory;

import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;

@Mod(ConfigurableKeepInventory.MODID)
public class ConfigurableKeepInventory {

    public static final String MODID = "keepinventory";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ConfigurableKeepInventory(ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, (IConfigSpec)KIConfig.SPEC);
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerRespawnEvent event)
    {
        LOGGER.info("[event] HELLO from player respawn event ", event);
    }
}