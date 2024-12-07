package ru.astemir.keepinventory;

import net.minecraft.world.effect.MobEffectInstance;

import java.util.Map;
import java.util.UUID;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;

public class EffectCache {
    private static final Map<UUID, Collection<MobEffectInstance>> effectMap = new HashMap<>();

    public static void store(UUID playerId, Collection<MobEffectInstance> effects) {
        if (effects != null && !effects.isEmpty()) {
            effectMap.put(playerId, new ArrayList<>(effects));
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
        }
    }
}