package io.github.icecron.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.dolphin.Dolphin;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Dolphin.class)
public abstract class MixinDolphinPickup {

    @org.spongepowered.asm.mixin.Unique
    private long ssl_dolphinThrowWindowStart = 0L;

    @org.spongepowered.asm.mixin.Unique
    private int ssl_dolphinThrowsInWindow = 0;

    @Inject(method = "pickUpItem", at = @At("HEAD"), cancellable = true)
    private void onPickUpItemHead(ServerLevel level, ItemEntity entity, CallbackInfo ci) {
        Dolphin self = (Dolphin) (Object) this;

        // Only intercept when main hand is empty to preserve normal behavior otherwise
        if (!self.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
            return;
        }

        // Check feature toggle
        if (!io.github.icecron.CarpetSslAdditionSettings.dolphinPickupIntercept) return;

        ItemStack stack = entity.getItem();
        // Conservative fallback: if stack is empty, do nothing. If non-empty, proceed with intercept.
        if (stack.isEmpty()) {
            return;
        }

        // Rate limiting: sliding window based on world game time
        long now = level.getGameTime();
        int window = 40;
        int max = 5;
        int penalty = 40;

        if (now - this.ssl_dolphinThrowWindowStart >= window) {
            this.ssl_dolphinThrowWindowStart = now;
            this.ssl_dolphinThrowsInWindow = 0;
        }

        if (this.ssl_dolphinThrowsInWindow >= max) {
            // Too many throws in window: apply a pickup delay to the item so it won't be immediately grabbed/processed
            entity.setPickUpDelay(penalty);
            // Do not apply velocity nor cancel original to avoid equipping; just exit
            ci.cancel();
            return;
        }

        // Recreate the dolphin spit/drop velocity so the item is thrown away instead of being equipped
        float dir = level.getRandom().nextFloat() * ((float) Math.PI * 2F);
        float pow2 = 0.02F * level.getRandom().nextFloat();

        double yRotRad = self.getYRot() * ((float) Math.PI / 180F);
        double xRotRad = self.getXRot() * ((float) Math.PI / 180F);

        double vx = 0.3F * -Mth.sin((float) yRotRad) * Mth.cos((float) xRotRad) + Math.cos(dir) * pow2;
        double vy = 0.3F * Mth.sin((float) xRotRad) * 1.5F;
        double vz = 0.3F * Mth.cos((float) yRotRad) * Mth.cos((float) xRotRad) + Math.sin(dir) * pow2;

        // Move the existing item to the dolphin's head position so it appears thrown from the dolphin
        try {
            entity.setPos(self.getX(), self.getEyeY() - 0.3D, self.getZ());
        } catch (Throwable ignored) {
            // Fallback: if setPos is not available on this mapping, try teleport or other methods via reflection
            try {
                java.lang.reflect.Method m = entity.getClass().getMethod("setPos", double.class, double.class, double.class);
                m.invoke(entity, self.getX(), self.getEyeY() - 0.3D, self.getZ());
            } catch (Throwable ignored2) {
                // ignore; if unable to move, the velocity will still be applied at current position
            }
        }

        entity.setDeltaMovement(vx, vy, vz);
        entity.setPickUpDelay(40);

        try {
            entity.setThrower(self);
        } catch (Throwable ignored) {
            // ignore if signature differs across mappings
        }

        // Count this throw and cancel original pick up so dolphin does not equip the item or trigger take()/animation lock
        this.ssl_dolphinThrowsInWindow++;
        ci.cancel();
    }
}
