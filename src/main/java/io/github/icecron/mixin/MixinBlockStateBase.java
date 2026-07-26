package io.github.icecron.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.icecron.CarpetSslAdditionSettings;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class MixinBlockStateBase {
    @Shadow
    public abstract Block getBlock();

    @Inject(method = "getDestroySpeed", at = @At("TAIL"), cancellable = true)
    public void getBlockHardness(BlockGetter blockGetter, BlockPos blockPos, CallbackInfoReturnable<Float> cir) {
        if (this.getBlock() == Blocks.DEEPSLATE && CarpetSslAdditionSettings.softDeepslate) {
            cir.setReturnValue(Blocks.STONE.defaultBlockState().getDestroySpeed(blockGetter, blockPos));
            cir.cancel();
        }

        if (this.getBlock() == Blocks.OBSIDIAN && CarpetSslAdditionSettings.softObsidian) {
            cir.setReturnValue(Blocks.END_STONE.defaultBlockState().getDestroySpeed(blockGetter, blockPos));
            cir.cancel();
        }
    }
}
