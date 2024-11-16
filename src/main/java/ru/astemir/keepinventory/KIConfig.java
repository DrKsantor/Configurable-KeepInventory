package ru.astemir.keepinventory;

import java.util.List;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = "keepinventory", bus = EventBusSubscriber.Bus.MOD)
public class KIConfig
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.ConfigValue<Boolean> ENABLED = (ModConfigSpec.ConfigValue<Boolean>)BUILDER
        .comment("Mod functionality enabled.")
        .define("modEnabled", true);
     
    public static final ModConfigSpec.ConfigValue<Boolean> KEEP_EXPERIENCE = (ModConfigSpec.ConfigValue<Boolean>)BUILDER
        .comment("Keep player experience after death.")
        .define("keepExperience", true);
    public static final ModConfigSpec.ConfigValue<Boolean> KEEP_SCORE = (ModConfigSpec.ConfigValue<Boolean>)BUILDER
         .comment("Keep player score after death.")
        .define("keepScore", true);
     
    public static final ModConfigSpec.ConfigValue<Boolean> KEEP_HUNGER = (ModConfigSpec.ConfigValue<Boolean>)BUILDER
        .comment("Keep player hunger after death.")
        .define("keepHunger", false);
     
    public static final ModConfigSpec.ConfigValue<Boolean> KEEP_SATURATION = (ModConfigSpec.ConfigValue<Boolean>)BUILDER
        .comment("Keep player saturation after death.")
        .define("keepSaturation", false);
    public static final ModConfigSpec.ConfigValue<Boolean> KEEP_POTION_EFFECTS = (ModConfigSpec.ConfigValue<Boolean>)BUILDER
        .comment("Keep player potion effects after death.")
        .define("keepPotionEffects", false);  
            
    public static final ModConfigSpec.ConfigValue<List<? extends Object>> KEEPED_SLOTS = BUILDER
        .comment("A list of slots ids for keep inventory.")
        .defineList("keepedSlots", List.of((Object[]) new Integer[] { 
            Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), 
            Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), 
            Integer.valueOf(8), Integer.valueOf(36), Integer.valueOf(37), Integer.valueOf(38), 
            Integer.valueOf(39), Integer.valueOf(40), Integer.valueOf(45) 
        }), id -> true);

    // public static final ModConfigSpec.ConfigValue<List<? extends Integer>> KEEPED_SLOTS = BUILDER
    //     .comment("A list of slots ids for keep inventory.")
    //     .defineList("keepedSlots", List.of((Object[]) new Integer[] { 
    //         Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), 
    //         Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), 
    //         Integer.valueOf(8), Integer.valueOf(36), Integer.valueOf(37), Integer.valueOf(38), 
    //         Integer.valueOf(39), Integer.valueOf(40), Integer.valueOf(45) 
    //     }), id -> true);

    // public static final ModConfigSpec.ConfigValue<List<Integer>> KEEPED_SLOTS = BUILDER
    // .comment("A list of slots ids for keep inventory.")
    // .defineList("keepedSlots",
    //     List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 36, 37, 38, 39, 40, 45),
    //     obj -> obj instanceof Integer
    // );

    public static final ModConfigSpec.ConfigValue<Double> KEEPED_EXPERIENCE_MODIFIER = BUILDER
        .comment("Modifier that would be multiplied by your previous experience amount.")
        .define("keepedExperienceModifier", Double.valueOf(1.0D));
     
    public static final ModConfigSpec.ConfigValue<Double> KEEPED_HUNGER_MODIFIER = BUILDER
        .comment("Modifier that would be multiplied by your previous hunger amount.")
        .define("keepedHungerModifier", Double.valueOf(1.0D));
     
    public static final ModConfigSpec.ConfigValue<Integer> KEEPED_HUNGER_MIN_LIMIT = BUILDER
        .comment("Minumal value of hunger after your death, to prevent spawn with empty hunger bar.")
        .define("keepedHungerMinLimit", Integer.valueOf(1));
    
    public static final ModConfigSpec.ConfigValue<Double> KEEPED_SATURATION_MODIFIER = BUILDER
        .comment("Modifier that would be multiplied by your previous saturation amount.")
        .define("keepedSaturationModifier", Double.valueOf(1.0D));
    
    static final ModConfigSpec SPEC = BUILDER.build();
}