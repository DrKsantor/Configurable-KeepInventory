/*    */ package ru.astemir.keepinventory.mixin;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.effect.MobEffectInstance;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.entity.player.ProfilePublicKey;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.Level;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import ru.astemir.keepinventory.KIConfig;
/*    */ 
/*    */ @Mixin({ServerPlayer.class})
/*    */ public abstract class MixinServerPlayer extends Player {
/*    */   public MixinServerPlayer(Level p_219727_, BlockPos p_219728_, float p_219729_, GameProfile p_219730_, @Nullable ProfilePublicKey p_219731_) {
/* 23 */     super(p_219727_, p_219728_, p_219729_, p_219730_, p_219731_);
/*    */   }
/*    */   
/*    */   @Inject(method = {"restoreFrom"}, at = {@At("TAIL")})
/*    */   public void _OnRestoreFrom(ServerPlayer player, boolean p_9017_, CallbackInfo ci) {
/* 28 */     if (!p_9017_ && ((Boolean)KIConfig.ENABLED.get()).booleanValue()) {
/* 29 */       if (((Boolean)KIConfig.KEEP_EXPERIENCE.get()).booleanValue()) {
/* 30 */         this.f_36078_ = (int)(player.f_36078_ * ((Double)KIConfig.KEEPED_EXPERIENCE_MODIFIER.get()).doubleValue());
/* 31 */         this.f_36079_ = player.f_36079_;
/* 32 */         this.f_36080_ = player.f_36080_;
/*    */       } 
/* 34 */       if (((Boolean)KIConfig.KEEP_HUNGER.get()).booleanValue()) {
/* 35 */         int hungerLevel = (int)(player.m_36324_().m_38702_() * ((Double)KIConfig.KEEPED_HUNGER_MODIFIER.get()).doubleValue());
/* 36 */         this.f_36097_.m_38705_(Math.max(hungerLevel, ((Integer)KIConfig.KEEPED_HUNGER_MIN_LIMIT.get()).intValue()));
/*    */       } 
/* 38 */       if (((Boolean)KIConfig.KEEP_SATURATION.get()).booleanValue()) {
/* 39 */         this.f_36097_.m_38717_((float)(player.m_36324_().m_38722_() * ((Double)KIConfig.KEEPED_SATURATION_MODIFIER.get()).doubleValue()));
/*    */       }
/* 41 */       if (((Boolean)KIConfig.KEEP_POTION_EFFECTS.get()).booleanValue()) {
/* 42 */         for (MobEffectInstance activeEffect : player.m_21220_()) {
/* 43 */           m_7292_(activeEffect);
/*    */         }
/*    */       }
/* 46 */       if (((Boolean)KIConfig.KEEP_SCORE.get()).booleanValue()) {
/* 47 */         m_36397_(player.m_36344_());
/*    */       }
/* 49 */       for (Iterator<Integer> iterator = ((List)KIConfig.KEEPED_SLOTS.get()).iterator(); iterator.hasNext(); ) { int slotId = ((Integer)iterator.next()).intValue();
/* 50 */         ItemStack itemStack = player.m_150109_().m_8020_(slotId);
/* 51 */         if (itemStack != null)
/* 52 */           m_150109_().m_6836_(slotId, itemStack);  }
/*    */     
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\ksantor\Documents\repo\ConfigurableInventory\ConfigurableKeepInventory-1.19.2-1.11.jar!\ru\astemir\keepinventory\mixin\MixinServerPlayer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */