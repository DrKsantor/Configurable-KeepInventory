package ru.astemir.keepinventory;

import net.minecraft.world.effect.MobEffectInstance;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

import java.util.Map;
import java.util.UUID;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;

public class EffectCache {
    private static final Map<UUID, Collection<MobEffectInstance>> effectMap = new HashMap<>();

    private static final Logger LOGGER = LogUtils.getLogger();

    public static void store(UUID playerId, Collection<MobEffectInstance> effects) {
        if (effects != null && !effects.isEmpty()) {
            effectMap.put(playerId, new ArrayList<>(effects)); // Deep copy to prevent external modifications
            LOGGER.info("[EffectCache] Stored effects for {}: {}", playerId, effects);
        } else {
            LOGGER.warn("[EffectCache] Attempted to store empty or null effects for {}", playerId);
        }
    }

    public static Collection<MobEffectInstance> retrieve(UUID playerId) {
        return effectMap.getOrDefault(playerId, new ArrayList<>());
    }

    public static boolean hasEffects(UUID playerId) {
        return effectMap.containsKey(playerId) && !effectMap.get(playerId).isEmpty();
    }

    public static void clear(UUID playerId) {
        if (effectMap.containsKey(playerId)) {
            effectMap.remove(playerId);
            LOGGER.info("[EffectCache] Cleared effects for {}", playerId);
        } else {
            LOGGER.warn("[EffectCache] No effects to clear for {}", playerId);
        }
    }
}