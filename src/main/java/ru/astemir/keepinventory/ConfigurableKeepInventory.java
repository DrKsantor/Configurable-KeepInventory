package ru.astemir.keepinventory;

import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.eventbus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.eventbus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ConfigurableKeepInventory.MODID)
public class ConfigurableKeepInventory {
    
    public static final String MODID = "keepinventory";

    public ConfigurableKeepInventory(ModContainer container) {
        NeoForge.EVENT_BUS.register(this);
        container.registerConfig(ModConfig.Type.COMMON, (IConfigSpec)KIConfig.SPEC);
    }
}