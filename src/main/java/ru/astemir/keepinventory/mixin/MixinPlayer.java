package ru.astemir.keepinventory.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
//import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.astemir.keepinventory.KIConfig;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(Player.class)
public abstract class MixinPlayer extends LivingEntity {

    @Shadow public abstract Inventory getInventory();

    @Shadow @Nullable public abstract ItemEntity drop(ItemStack paramItemStack, boolean paramBoolean1, boolean paramBoolean2);

    protected MixinPlayer(EntityType<? extends LivingEntity> entityType, Level level) {

        super(entityType, level);
    }

    //@Inject(method = "dropEquipment",at = @At("HEAD"),cancellable = true)
    @Inject(method = "dropEquipment",at = @At("HEAD"),cancellable = true)
    public void _onDropEquipment(CallbackInfo ci) {
        boolean keepInventory = level().getGameRules().getRule(GameRules.RULE_KEEPINVENTORY).get();
        if (!keepInventory) {
            if (KIConfig.ENABLED.get()) {
                List<? extends Integer> savedSlots = KIConfig.KEEPED_SLOTS.get();
                int containerSize = getInventory().getContainerSize();
                for(int i = 0; i < containerSize; ++i) {
                    ItemStack itemstack = getInventory().getItem(i);
                    if (!savedSlots.contains(i)) {
                        if (!itemstack.isEmpty()) {
//                            if (EnchantmentHelper.hasVanishingCurse(itemstack)) {
//                                getInventory().removeItemNoUpdate(i);
//                            }else{
                            drop(itemstack,true,false);
                            getInventory().setItem(i,ItemStack.EMPTY);
//                            } //TODO hasVanishingCurse not exist in current neoforge, so saving all items for now
                        }
                    }
                }
                ci.cancel();
            }
        }
    }

    @Inject(method = "getBaseExperienceReward",at = @At("HEAD"), cancellable = true)
    public void _onGetExperience(CallbackInfoReturnable<Integer> cir){
        boolean keepInventory = level().getGameRules().getRule(GameRules.RULE_KEEPINVENTORY).get();
        if (!keepInventory) {
            if (KIConfig.ENABLED.get() && KIConfig.KEEP_EXPERIENCE.get()) {
                cir.setReturnValue(0);
            }
        }
    }
}