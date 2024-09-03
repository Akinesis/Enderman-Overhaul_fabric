package tech.alexnijjar.endermanoverhaul.mixins.common;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.alexnijjar.endermanoverhaul.common.ModUtils;
import tech.alexnijjar.endermanoverhaul.common.registry.ModItems;
import tech.alexnijjar.endermanoverhaul.common.tags.ModEntityTypeTags;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow
    protected ItemStack useItem;

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(
        method = "blockUsingShield",
        at = @At("TAIL")
    )
    private void endermanoverhaul$blockUsingShield(LivingEntity attacker, CallbackInfo ci) {
        if (!useItem.is(ModItems.CORRUPTED_SHIELD.get())) return;
        if (attacker.getType().is(ModEntityTypeTags.CANT_BE_TELEPORTED)) return;

        if (attacker.level().random.nextInt(4) != 0) {
            ModUtils.teleportTarget(attacker.level(), attacker, 32);
            attacker.hurt(attacker.damageSources().fall(), 2.0f);
        }
    }
}
