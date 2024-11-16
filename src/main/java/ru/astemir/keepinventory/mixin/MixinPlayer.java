// package ru.astemir.keepinventory.mixin;

// import java.util.List;
// import javax.annotation.Nullable;
// import net.minecraft.world.entity.EntityType;
// import net.minecraft.world.entity.LivingEntity;
// import net.minecraft.world.entity.item.ItemEntity;
// import net.minecraft.world.entity.player.Inventory;
// import net.minecraft.world.entity.player.Player;
// import net.minecraft.world.item.ItemStack;
// import net.minecraft.world.item.enchantment.EnchantmentHelper;
// import net.minecraft.world.level.GameRules;
// import net.minecraft.world.level.Level;
// import org.spongepowered.asm.mixin.Mixin;
// import org.spongepowered.asm.mixin.Shadow;
// import org.spongepowered.asm.mixin.injection.At;
// import org.spongepowered.asm.mixin.injection.Inject;
// import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
// import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
// import ru.astemir.keepinventory.KIConfig;

//@Mixin({Player.class})
//public abstract class MixinPlayer extends LivingEntity {
// /*    */   @Shadow
// /*    */   public abstract Inventory m_150109_();
// /*    */   
// /*    */   @Shadow
// /*    */   @Nullable
// /*    */   public abstract ItemEntity m_7197_(ItemStack paramItemStack, boolean paramBoolean1, boolean paramBoolean2);
// /*    */   
// /*    */   protected MixinPlayer(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
// /* 32 */     super(p_20966_, p_20967_);
// /*    */   }
// /*    */   
// /*    */   @Inject(method = {"dropEquipment"}, at = {@At("HEAD")}, cancellable = true)
// /*    */   public void _onDropEquipment(CallbackInfo ci) {
// /* 37 */     boolean keepInventory = ((GameRules.BooleanValue)this.f_19853_.m_46469_().m_46170_(GameRules.f_46133_)).m_46223_();
// /* 38 */     if (!keepInventory && (
// /* 39 */       (Boolean)KIConfig.ENABLED.get()).booleanValue()) {
// /* 40 */       List<? extends Integer> savedSlots = (List<? extends Integer>)KIConfig.KEEPED_SLOTS.get();
// /* 41 */       int containerSize = m_150109_().m_6643_();
// /* 42 */       for (int i = 0; i < containerSize; i++) {
// /* 43 */         ItemStack itemstack = m_150109_().m_8020_(i);
// /* 44 */         if (!savedSlots.contains(Integer.valueOf(i)) && 
// /* 45 */           !itemstack.m_41619_()) {
// /* 46 */           if (EnchantmentHelper.m_44924_(itemstack)) {
// /* 47 */             m_150109_().m_8016_(i);
// /*    */           } else {
// /* 49 */             m_7197_(itemstack, true, false);
// /* 50 */             m_150109_().m_6836_(i, ItemStack.f_41583_);
// /*    */           } 
// /*    */         }
// /*    */       } 
// /*    */       
// /* 55 */       ci.cancel();
// /*    */     } 
// /*    */   }
// /*    */ 
// /*    */   
// /*    */   @Inject(method = {"getExperienceReward"}, at = {@At("HEAD")}, cancellable = true)
// /*    */   public void _onGetExperience(CallbackInfoReturnable<Integer> cir) {
// /* 62 */     boolean keepInventory = ((GameRules.BooleanValue)this.f_19853_.m_46469_().m_46170_(GameRules.f_46133_)).m_46223_();
// /* 63 */     if (!keepInventory && (
// /* 64 */       (Boolean)KIConfig.ENABLED.get()).booleanValue() && ((Boolean)KIConfig.KEEP_EXPERIENCE.get()).booleanValue())
// /* 65 */       cir.setReturnValue(Integer.valueOf(0)); 
// /*    */   }
//}