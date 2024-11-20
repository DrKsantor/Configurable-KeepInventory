package ru.astemir.keepinventory;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.fml.common.EventBusSubscriber;

import java.util.List;

@EventBusSubscriber(modid = ConfigurableKeepInventory.MODID, bus = EventBusSubscriber.Bus.MOD)
public class KIConfig
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.ConfigValue<Boolean> ENABLED = BUILDER
            .translation("keepinventory.config.modEnabled")
            .comment("Mod functionality enabled.")
            .define("modEnabled",true);

    public static final ModConfigSpec.ConfigValue<Boolean> KEEP_EXPERIENCE = BUILDER
            .translation("keepinventory.config.keepExperience")
            .comment("Keep player experience after death.")
            .define("keepExperience",true);
    public static final ModConfigSpec.ConfigValue<Boolean> KEEP_SCORE = BUILDER
            .translation("keepinventory.config.keepScore")
            .comment("Keep player score after death.")
            .define("keepScore",true);

    public static final ModConfigSpec.ConfigValue<Boolean> KEEP_HUNGER = BUILDER
            .translation("keepinventory.config.keepHunger")
            .comment("Keep player hunger after death.")
            .define("keepHunger",false);

    public static final ModConfigSpec.ConfigValue<Boolean> KEEP_SATURATION = BUILDER
            .translation("keepinventory.config.keepSaturation")
            .comment("Keep player saturation after death.")
            .define("keepSaturation",false);

    public static final ModConfigSpec.ConfigValue<Boolean> KEEP_POTION_EFFECTS = BUILDER
            .translation("keepinventory.config.keepPotionEffects")
            .comment("Keep player potion effects after death.")
            .define("keepPotionEffects",false);


    public static final ModConfigSpec.ConfigValue<List<? extends Integer>> KEEPED_SLOTS = BUILDER
            .translation("keepinventory.config.keepedSlots")
            .comment("A list of slots ids for keep inventory.")
            .defineList("keepedSlots", List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 36, 37, 38, 39, 40, 45), (id)->true);
    public static final ModConfigSpec.ConfigValue<Double> KEEPED_EXPERIENCE_MODIFIER = BUILDER
            .translation("keepinventory.config.keepedExperienceModifier")
            .comment("Modifier that would be multiplied by your previous experience amount.")
            .define("keepedExperienceModifier",1.0);

    public static final ModConfigSpec.ConfigValue<Double> KEEPED_HUNGER_MODIFIER = BUILDER
            .translation("keepinventory.config.keepedHungerModifier")
            .comment("Modifier that would be multiplied by your previous hunger amount.")
            .define("keepedHungerModifier",1.0);

    public static final ModConfigSpec.ConfigValue<Integer> KEEPED_HUNGER_MIN_LIMIT = BUILDER
            .translation("keepinventory.config.keepedHungerMinLimit")
            .comment("Minimal value of hunger after your death, to prevent spawn with empty hunger bar.")
            .define("keepedHungerMinLimit",1);
    public static final ModConfigSpec.ConfigValue<Double> KEEPED_SATURATION_MODIFIER = BUILDER
            .translation("keepinventory.config.keepedSaturationModifier")
            .comment("Modifier that would be multiplied by your previous saturation amount.")
            .define("keepedSaturationModifier",1.0);
    static final ModConfigSpec SPEC = BUILDER.build();

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event){}
}