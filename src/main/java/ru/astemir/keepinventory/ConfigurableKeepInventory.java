/*    */ package ru.astemir.keepinventory;
/*    */ 
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.fml.ModLoadingContext;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.config.IConfigSpec;
/*    */ import net.minecraftforge.fml.config.ModConfig;
/*    */ 
/*    */ @Mod("keepinventory")
/*    */ public class ConfigurableKeepInventory {
/*    */   public static final String MODID = "keepinventory";
/*    */   
/*    */   public ConfigurableKeepInventory() {
/* 14 */     MinecraftForge.EVENT_BUS.register(this);
/* 15 */     ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, (IConfigSpec)KIConfig.SPEC);
/*    */   }
/*    */ }


/* Location:              C:\Users\ksantor\Documents\repo\ConfigurableInventory\ConfigurableKeepInventory-1.19.2-1.11.jar!\ru\astemir\keepinventory\ConfigurableKeepInventory.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */