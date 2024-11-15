/*    */ package ru.astemir.keepinventory;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ 
/*    */ @EventBusSubscriber(modid = "keepinventory", bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class KIConfig
/*    */ {
/* 11 */   private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
/*    */   
/* 13 */   public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLED = (ForgeConfigSpec.ConfigValue<Boolean>)BUILDER
/* 14 */     .comment("Mod functionality enabled.")
/* 15 */     .define("modEnabled", true);
/*    */   
/* 17 */   public static final ForgeConfigSpec.ConfigValue<Boolean> KEEP_EXPERIENCE = (ForgeConfigSpec.ConfigValue<Boolean>)BUILDER
/* 18 */     .comment("Keep player experience after death.")
/* 19 */     .define("keepExperience", true);
/* 20 */   public static final ForgeConfigSpec.ConfigValue<Boolean> KEEP_SCORE = (ForgeConfigSpec.ConfigValue<Boolean>)BUILDER
/* 21 */     .comment("Keep player score after death.")
/* 22 */     .define("keepScore", true);
/*    */   
/* 24 */   public static final ForgeConfigSpec.ConfigValue<Boolean> KEEP_HUNGER = (ForgeConfigSpec.ConfigValue<Boolean>)BUILDER
/* 25 */     .comment("Keep player hunger after death.")
/* 26 */     .define("keepHunger", false);
/*    */   
/* 28 */   public static final ForgeConfigSpec.ConfigValue<Boolean> KEEP_SATURATION = (ForgeConfigSpec.ConfigValue<Boolean>)BUILDER
/* 29 */     .comment("Keep player saturation after death.")
/* 30 */     .define("keepSaturation", false);
/*    */   
/* 32 */   public static final ForgeConfigSpec.ConfigValue<Boolean> KEEP_POTION_EFFECTS = (ForgeConfigSpec.ConfigValue<Boolean>)BUILDER
/* 33 */     .comment("Keep player potion effects after death.")
/* 34 */     .define("keepPotionEffects", false);
/*    */   
            public static final ForgeConfigSpec.ConfigValue<List<? extends Integer>> KEEPED_SLOTS = BUILDER
                .comment("A list of slots ids for keep inventory.")
                .defineList("keepedSlots", List.of((Object[]) new Integer[] { 
                Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), 
                Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), 
                Integer.valueOf(8), Integer.valueOf(36), Integer.valueOf(37), Integer.valueOf(38), 
                Integer.valueOf(39), Integer.valueOf(40), Integer.valueOf(45) 
                }), id -> true);
            public static final ForgeConfigSpec.ConfigValue<Double> KEEPED_EXPERIENCE_MODIFIER = BUILDER
/* 40 */     .comment("Modifier that would be multiplied by your previous experience amount.")
/* 41 */     .define("keepedExperienceModifier", Double.valueOf(1.0D));
/*    */   
/* 43 */   public static final ForgeConfigSpec.ConfigValue<Double> KEEPED_HUNGER_MODIFIER = BUILDER
/* 44 */     .comment("Modifier that would be multiplied by your previous hunger amount.")
/* 45 */     .define("keepedHungerModifier", Double.valueOf(1.0D));
/*    */   
/* 47 */   public static final ForgeConfigSpec.ConfigValue<Integer> KEEPED_HUNGER_MIN_LIMIT = BUILDER
/* 48 */     .comment("Minumal value of hunger after your death, to prevent spawn with empty hunger bar.")
/* 49 */     .define("keepedHungerMinLimit", Integer.valueOf(1));
/* 50 */   public static final ForgeConfigSpec.ConfigValue<Double> KEEPED_SATURATION_MODIFIER = BUILDER
/* 51 */     .comment("Modifier that would be multiplied by your previous saturation amount.")
/* 52 */     .define("keepedSaturationModifier", Double.valueOf(1.0D));
/* 53 */   static final ForgeConfigSpec SPEC = BUILDER.build();
/*    */ }


/* Location:              C:\Users\ksantor\Documents\repo\ConfigurableInventory\ConfigurableKeepInventory-1.19.2-1.11.jar!\ru\astemir\keepinventory\KIConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */