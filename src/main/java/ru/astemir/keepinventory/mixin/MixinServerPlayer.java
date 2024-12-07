package ru.astemir.keepinventory.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.astemir.keepinventory.EffectCache;
import ru.astemir.keepinventory.KIConfig;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import java.util.Collection;
import java.util.UUID;

@Mixin(ServerPlayer.class)
public abstract class MixinServerPlayer extends Player {

    private static final Logger LOGGER = LogUtils.getLogger();

    public MixinServerPlayer(Level p_250508_, BlockPos p_250289_, float p_251702_, GameProfile p_252153_) {
        super(p_250508_, p_250289_, p_251702_, p_252153_);
    }

    @Inject(method = "die", at = @At("HEAD"))
    public void onPlayerDeath(DamageSource source, CallbackInfo ci) {
        if ((Object) this instanceof ServerPlayer player) {
            if (KIConfig.KEEP_POTION_EFFECTS.get()) {
                if (KIConfig.KEEP_POTION_EFFECTS.get()) {
                    UUID playerId = player.getUUID();
                    Collection<MobEffectInstance> activeEffects = getActiveEffects();

                    if (!activeEffects.isEmpty()) {
                        EffectCache.store(playerId, activeEffects);
                    }

                    LOGGER.info("[Mixin] Stored effects for player {}: {}", playerId, activeEffects);
                }
            }
        }
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
                UUID playerId = player.getUUID();

                if (EffectCache.hasEffects(playerId)) {
                    Collection<MobEffectInstance> storedEffects = EffectCache.retrieve(playerId);
                    for (MobEffectInstance effect : storedEffects) {
                        this.addEffect(effect);
                    }

                    LOGGER.info("[Mixin] Restored effects for player {}: {}", playerId, storedEffects);
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