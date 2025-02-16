package ru.astemir.keepinventory;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.fml.common.EventBusSubscriber;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public static final ModConfigSpec.ConfigValue<Boolean> KEEP_ALL_SLOTS = BUILDER
            .translation("keepinventory.config.keepAllSlots")
            .comment("Keep all slots without clarifying like vanilla rule.")
            .define("keepAllSlots", false);

    public static final ModConfigSpec.ConfigValue<String> KEEPED_SLOTS = BUILDER
            .translation("keepinventory.config.keepedSlots")
            .comment("A comma-separated list of slot IDs to keep inventory, using strict format. Example: '0,1,2,36,37'")
            .define("keepedSlots", "0,1,2,3,4,5,6,7,8,36,37,38,39,40,45");

    public static final ModConfigSpec.ConfigValue<String> KEEPED_ITEMS = BUILDER
            .translation("keepinventory.config.keepedItems")
            .comment("A comma-separated list of item IDs to keep after death, using strict format. Example: 'minecraft:torch,minecraft:arrow'")
            .define("keepedItems", "minecraft:torch,minecraft:arrow");

    public static final ModConfigSpec.ConfigValue<Double> KEEPED_EXPERIENCE_MODIFIER = BUILDER
            .translation("keepinventory.config.keepedExperienceModifier")
            .comment("Modifier that would be multiplied by your previous experience amount. Enabled keep experience required.")
            .define("keepedExperienceModifier",1.0);

    public static final ModConfigSpec.ConfigValue<Double> KEEPED_HUNGER_MODIFIER = BUILDER
            .translation("keepinventory.config.keepedHungerModifier")
            .comment("Modifier that would be multiplied by your previous hunger amount. Enabled keep hunger required.")
            .define("keepedHungerModifier",1.0);

    public static final ModConfigSpec.ConfigValue<Integer> KEEPED_HUNGER_MIN_LIMIT = BUILDER
            .translation("keepinventory.config.keepedHungerMinLimit")
            .comment("Minimal value of hunger after your death, to prevent spawn with empty hunger bar.")
            .define("keepedHungerMinLimit",8);
    public static final ModConfigSpec.ConfigValue<Double> KEEPED_SATURATION_MODIFIER = BUILDER
            .translation("keepinventory.config.keepedSaturationModifier")
            .comment("Modifier that would be multiplied by your previous saturation amount. Enabled keep saturation required.")
            .define("keepedSaturationModifier",1.0);
    static final ModConfigSpec SPEC = BUILDER.build();

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event){}

    public static List<? extends Integer> parseKeepedSlots(@NotNull String slots) {
        try {
            return Arrays.stream(slots.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            return List.of();
        }
    }

    public static List<String> parseKeepedItems(@NotNull String items) {
        try {
            return Arrays.stream(items.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return List.of();
        }
    }
}