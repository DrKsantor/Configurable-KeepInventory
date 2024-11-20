package ru.astemir.keepinventory.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.ProfilePublicKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.astemir.keepinventory.KIConfig;

import java.util.Iterator;
import java.util.List;

@Mixin(ServerPlayer.class)
public abstract class MixinServerPlayer extends Player {

    public MixinServerPlayer(Level p_250508_, BlockPos p_250289_, float p_251702_, GameProfile p_252153_) {
        super(p_250508_, p_250289_, p_251702_, p_252153_);
    }

    @Inject(method = "restoreFrom", at=@At("TAIL"))
    public void _OnRestoreFrom(ServerPlayer player, boolean p_9017_, CallbackInfo ci){
        if (!p_9017_ && KIConfig.ENABLED.get()){
            if (KIConfig.KEEP_EXPERIENCE.get()){
                this.experienceLevel = (int) (player.experienceLevel*KIConfig.KEEPED_EXPERIENCE_MODIFIER.get());
                this.totalExperience =  player.totalExperience;
                this.experienceProgress = player.experienceProgress;
            }
            if (KIConfig.KEEP_HUNGER.get()){
                int hungerLevel = (int) (player.getFoodData().getFoodLevel()*KIConfig.KEEPED_HUNGER_MODIFIER.get());
                this.foodData.setFoodLevel(Math.max(hungerLevel,KIConfig.KEEPED_HUNGER_MIN_LIMIT.get()));
            }
            if (KIConfig.KEEP_SATURATION.get()){
                this.foodData.setSaturation((float) (player.getFoodData().getSaturationLevel()*KIConfig.KEEPED_SATURATION_MODIFIER.get()));
            }
            if (KIConfig.KEEP_POTION_EFFECTS.get()){
                for (MobEffectInstance activeEffect : player.getActiveEffects()) {
                    this.addEffect(activeEffect);
                }
            }
            if (KIConfig.KEEP_SCORE.get()){
                this.setScore(player.getScore());
            }
            for (int slotId : KIConfig.KEEPED_SLOTS.get()) {
                ItemStack itemStack = player.getInventory().getItem(slotId);
                if (itemStack != null) {
                    this.getInventory().setItem(slotId, itemStack);
                }
            }
        }
    }
}