package com.swingspeed.mixin;

import com.swingspeed.config.SwingSpeedConfig;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(method = "getHandSwingDuration", at = @At("RETURN"), cancellable = true)
    private void swingspeed$modifyHandSwingDuration(CallbackInfoReturnable<Integer> cir) {
        SwingSpeedConfig cfg = SwingSpeedConfig.getInstance();
        
        if (cfg != null && cfg.enabled) {
            int original = cir.getReturnValue();
            float multiplier = cfg.swingSpeedMultiplier;
            
            float calculatedValue = (float) original / Math.max(multiplier, 0.1f);
            int modified = Math.round(calculatedValue);
            
            if (modified < 1) {
                modified = 1;
            }
            
            cir.setReturnValue(modified);
        }
    }
}
