package ru.astemir.keepinventory;

import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.fml.config.ModConfig;

@Mod(ConfigurableKeepInventory.MODID)
public class ConfigurableKeepInventory {

    public static final String MODID = "keepinventory";

    public ConfigurableKeepInventory(ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, (IConfigSpec)KIConfig.SPEC);
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}