package io.github.icecron.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.icecron.CarpetSslAdditionSettings;
import io.github.icecron.utils.EnderPearlChunkLoader;

@Mixin(ThrownEnderpearl.class)
public abstract class MixinThrownEnderpearl extends ThrowableItemProjectile {
    @Unique
    private long ticketTimer = 0L;

    protected MixinThrownEnderpearl(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/throwableitemprojectile/ThrownEnderpearl;discard()V"))
    private void onHitDiscard(CallbackInfo ci) {
        ThrownEnderpearl thrownEnderpearl = (ThrownEnderpearl) (Object) this;
        if (CarpetSslAdditionSettings.enderPearlChunkLoader) {
            EnderPearlChunkLoader.deregisterEnderPearl(thrownEnderpearl);
        }
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/throwableitemprojectile/ThrownEnderpearl;discard()V"))
    private void onTickDiscard(CallbackInfo ci) {
        ThrownEnderpearl thrownEnderpearl = (ThrownEnderpearl) (Object) this;
        if (CarpetSslAdditionSettings.enderPearlChunkLoader) {
            EnderPearlChunkLoader.registerEnderPearl(thrownEnderpearl);
        }
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void onTick(CallbackInfo ci) {
        ThrownEnderpearl thrownEnderpearl = (ThrownEnderpearl) (Object) this;
        if (CarpetSslAdditionSettings.enderPearlChunkLoader) {
            int i;
            int j;
            Entity entity;
            label30: {
                i = SectionPos.blockToSectionCoord(thrownEnderpearl.position().x());
                j = SectionPos.blockToSectionCoord(thrownEnderpearl.position().z());
                entity = thrownEnderpearl.getOwner();
                if (entity instanceof ServerPlayer serverPlayer) {
                    boolean enderPearlsVanishOnDeath = Boolean.TRUE.equals(
                            ((ServerLevel) serverPlayer.level()).getGameRules()
                                    .get(GameRules.ENDER_PEARLS_VANISH_ON_DEATH));
                    if (!entity.isAlive() && enderPearlsVanishOnDeath) {
                        thrownEnderpearl.discard();
                        break label30;
                    }
                }

                super.tick();
            }

            if (thrownEnderpearl.isAlive()) {
                BlockPos blockPos = BlockPos.containing(thrownEnderpearl.position());
                if ((--this.ticketTimer <= 0L || i != SectionPos.blockToSectionCoord(blockPos.getX())
                        || j != SectionPos.blockToSectionCoord(blockPos.getZ()))
                        && entity instanceof ServerPlayer serverPlayer2) {
                    this.ticketTimer = EnderPearlChunkLoader.registerAndUpdateEnderPearlTicket(thrownEnderpearl);
                }
            }
            ci.cancel();
        }
    }
}
