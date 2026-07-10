package com.rainforestkongra.event;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import com.rainforestkongra.item.KongraArmorMaterial;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Handles the "rainforest rain drains life" mechanic.
 * Players exposed to rain (with open sky above) for a prolonged period begin
 * to lose health. Wearing a full KONGRA armor set fully protects the player.
 * Wearing partial KONGRA armor slows the effect.
 */
public class RainDamageHandler {
    // Ticks a player must be continuously in rain before damage starts (20 seconds).
    private static final int EXPOSURE_THRESHOLD = 400;
    // How often damage is applied once threshold is reached (every 2 seconds).
    private static final int DAMAGE_INTERVAL = 40;

    private final Map<UUID, Integer> exposureTicks = new HashMap<>();

    public void onServerTick(MinecraftServer server) {
        for (ServerWorld world : server.getWorlds()) {
            for (ServerPlayerEntity player : world.getPlayers()) {
                tickPlayer(world, player);
            }
        }
    }

    private void tickPlayer(ServerWorld world, ServerPlayerEntity player) {
        UUID id = player.getUuid();

        if (player.isCreative() || player.isSpectator() || player.isDead()) {
            exposureTicks.remove(id);
            return;
        }

        boolean exposedToRain = isExposedToRain(world, player);

        if (!exposedToRain) {
            // Dry off gradually.
            Integer current = exposureTicks.get(id);
            if (current != null) {
                int next = current - 2;
                if (next <= 0) {
                    exposureTicks.remove(id);
                } else {
                    exposureTicks.put(id, next);
                }
            }
            return;
        }

        int fullKongraPieces = countKongraPieces(player);
        if (fullKongraPieces >= 4) {
            // Full set fully protects, and even dries the player off.
            exposureTicks.remove(id);
            return;
        }

        int accumulated = exposureTicks.getOrDefault(id, 0) + 1;
        exposureTicks.put(id, accumulated);

        if (accumulated >= EXPOSURE_THRESHOLD) {
            int overExposure = accumulated - EXPOSURE_THRESHOLD;
            if (overExposure % DAMAGE_INTERVAL == 0) {
                float damage = 2.0f - (fullKongraPieces * 0.4f);
                if (damage < 0.5f) {
                    damage = 0.5f;
                }
                DamageSource source = player.getDamageSources().magic();
                player.damage(source, damage);
            }
        }
    }

    private boolean isExposedToRain(ServerWorld world, PlayerEntity player) {
        if (!world.isRaining()) {
            return false;
        }
        BlockPos pos = player.getBlockPos();
        // Must be able to see the sky (no roof) and be at or above precipitation height.
        if (!world.isSkyVisible(pos)) {
            return false;
        }
        int topY = world.getTopY(net.minecraft.world.Heightmap.Type.MOTION_BLOCKING, pos.getX(), pos.getZ());
        return pos.getY() >= topY;
    }

    private int countKongraPieces(PlayerEntity player) {
        int count = 0;
        for (EquipmentSlot slot : new EquipmentSlot[]{
            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET}) {
            ItemStack stack = player.getEquippedStack(slot);
            if (stack.getItem() instanceof ArmorItem armorItem
                && armorItem.getMaterial() == KongraArmorMaterial.INSTANCE) {
                count++;
            }
        }
        return count;
    }
}