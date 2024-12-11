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

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

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
                }
            }
        }
    }

    @Inject(method = "restoreFrom", at = @At("TAIL"))
    public void _OnRestoreFrom(ServerPlayer player, boolean p_9017_, CallbackInfo ci) {
        if (!p_9017_ && KIConfig.ENABLED.get()) {
            if (KIConfig.KEEP_EXPERIENCE.get()) {
                this.experienceLevel = (int) (player.experienceLevel * KIConfig.KEEPED_EXPERIENCE_MODIFIER.get());
                this.totalExperience = player.totalExperience;
                this.experienceProgress = player.experienceProgress;
            }
            if (KIConfig.KEEP_HUNGER.get()) {
                int hungerLevel = (int) (player.getFoodData().getFoodLevel() * KIConfig.KEEPED_HUNGER_MODIFIER.get());
                this.foodData.setFoodLevel(Math.max(hungerLevel, KIConfig.KEEPED_HUNGER_MIN_LIMIT.get()));
            }
            if (KIConfig.KEEP_SATURATION.get()) {
                this.foodData.setSaturation((float) (player.getFoodData().getSaturationLevel() * KIConfig.KEEPED_SATURATION_MODIFIER.get()));
            }
            if (KIConfig.KEEP_POTION_EFFECTS.get()) {
                UUID playerId = player.getUUID();

                if (EffectCache.hasEffects(playerId)) {
                    Collection<MobEffectInstance> storedEffects = EffectCache.retrieve(playerId);
                    for (MobEffectInstance effect : storedEffects) {
                        this.addEffect(effect);
                    }
                }
            }
            if (KIConfig.KEEP_SCORE.get()) {
                this.setScore(player.getScore());
            }

            String slotsString = KIConfig.KEEPED_SLOTS.get();
            List<? extends Integer> savedSlots = KIConfig.parseKeepedSlots(slotsString);
            for (int slotId : savedSlots) {
                ItemStack itemStack = player.getInventory().getItem(slotId);
                if (itemStack != null) {
                    this.getInventory().setItem(slotId, itemStack);
                }
            }

            List<String> savedItems = KIConfig.parseKeepedItems(KIConfig.KEEPED_ITEMS.get());
            LOGGER.info("[MixinServerPlayer] Slots restored. Trying to restore items: {}", savedItems);
            for (int i = 0; i < this.getInventory().getContainerSize(); i++) {
                ItemStack itemStack = player.getInventory().getItem(i);
                if (itemStack.isEmpty()) continue;

                String itemName = itemStack.getItem().toString();
                LOGGER.info("[MixinServerPlayer] Currently checking item: {}, name:{}", itemStack, itemName);
                if (savedItems.contains(itemName)) {
                    LOGGER.info("[MixinServerPlayer] item in the save-list: slotId:{}, itemstack:{}", i, itemStack);
                    this.getInventory().setItem(i, itemStack);
                }
            }
        }
    }
}