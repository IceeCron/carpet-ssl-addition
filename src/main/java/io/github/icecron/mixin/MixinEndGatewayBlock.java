package io.github.icecron.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EndGatewayBlock;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.icecron.CarpetSslAdditionSettings;

import java.util.Set;

@Mixin(EndGatewayBlock.class)
public abstract class MixinEndGatewayBlock {
    @Inject(method = "getPortalDestination", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/portal/TeleportTransition;<init>(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;FFLjava/util/Set;Lnet/minecraft/world/level/portal/TeleportTransition$PostTeleportTransition;)V", shift = At.Shift.BEFORE), cancellable = true)
    private void injectGetPortalDestination(
            ServerLevel serverLevel,
            Entity entity,
            BlockPos blockPos,
            CallbackInfoReturnable<TeleportTransition> cir) {
        if (serverLevel == null || entity == null || blockPos == null) {
            return;
        }

        TheEndGatewayBlockEntity blockEntity = (TheEndGatewayBlockEntity) serverLevel.getBlockEntity(blockPos);
        if (blockEntity == null) {
            return;
        }

        Vec3 vec3 = blockEntity.getPortalPosition(serverLevel, blockPos);
        if (vec3 == null) {
            return;
        }

        TeleportTransition dimensionTransition = new TeleportTransition(
                serverLevel,
                vec3,
                Vec3.ZERO,
                0.0F,
                0.0F,
                entity instanceof ThrownEnderpearl ? Set.of()
                        : Relative.union(new Set[] { Relative.DELTA, Relative.ROTATION }),
                TeleportTransition.DO_NOTHING);

        if (CarpetSslAdditionSettings.endGatewayDoNotAddLoadTicket.equals("all")) {
            cir.setReturnValue(dimensionTransition);
        } else if (CarpetSslAdditionSettings.endGatewayDoNotAddLoadTicket.equals("bone_block")) {
            BlockState blockState = serverLevel.getBlockState(blockPos.below());
            if (blockState.getBlock() == Blocks.BONE_BLOCK) {
                cir.setReturnValue(dimensionTransition);
            }
        }
    }
}
