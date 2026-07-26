package io.github.icecron.utils;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class EnderPearlChunkLoader {
    private static final Map<ServerPlayer, Set<ThrownEnderpearl>> enderPearls = new ConcurrentHashMap<>();

    private static final TicketType ENDER_PEARL = TicketType.ENDER_PEARL;

    public static void registerEnderPearl(ThrownEnderpearl thrownEnderpearl) {
        if (thrownEnderpearl == null || thrownEnderpearl.isRemoved()) {
            return;
        }
        Entity owner = thrownEnderpearl.getOwner();
        if (!(owner instanceof ServerPlayer serverPlayer)) {
            return;
        }
        enderPearls.computeIfAbsent(serverPlayer, k -> ConcurrentHashMap.newKeySet()).add(thrownEnderpearl);
    }

    public static void deregisterEnderPearl(ThrownEnderpearl thrownEnderpearl) {
        if (thrownEnderpearl == null) {
            return;
        }
        Entity owner = thrownEnderpearl.getOwner();
        if (!(owner instanceof ServerPlayer serverPlayer)) {
            return;
        }
        Set<ThrownEnderpearl> pearls = enderPearls.get(serverPlayer);
        if (pearls == null) {
            return;
        }
        pearls.remove(thrownEnderpearl);
        if (pearls.isEmpty()) {
            enderPearls.remove(serverPlayer);
        }
    }

    public static Set<ThrownEnderpearl> getEnderPearls(ServerPlayer owner) {
        return enderPearls.getOrDefault(owner, Set.of());
    }

    public static long registerAndUpdateEnderPearlTicket(ThrownEnderpearl thrownEnderpearl) {
        if (thrownEnderpearl == null) {
            return 0L;
        }
        Level level = thrownEnderpearl.level();
        if (level instanceof ServerLevel serverLevel) {
            ChunkPos chunkPos = thrownEnderpearl.chunkPosition();
            registerEnderPearl(thrownEnderpearl);
            serverLevel.resetEmptyTime();
            return placeEnderPearlTicket(serverLevel, chunkPos) - 1L;
        } else {
            return 0L;
        }
    }

    public static long placeEnderPearlTicket(ServerLevel serverLevel, ChunkPos chunkPos) {
        if (serverLevel == null || chunkPos == null) {
            return 0L;
        }
        serverLevel.getChunkSource().addTicketWithRadius(ENDER_PEARL, chunkPos, 2);
        return 40L;
    }
}
