package tech.alexnijjar.endermanoverhaul.mixins.common;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.IronGolem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.alexnijjar.endermanoverhaul.common.entities.base.PassiveEnderman;
import tech.alexnijjar.endermanoverhaul.common.entities.pets.PetEnderman;

@Mixin(IronGolem.class)
public abstract class IronGolemMixin {

    @Inject(
        method = "lambda$registerGoals$0",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void endermanoverhaul$onRegisterAttackGoal(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof PassiveEnderman || entity instanceof PetEnderman) {
            cir.setReturnValue(false);
        }
    }
}