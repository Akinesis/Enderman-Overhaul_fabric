package tech.alexnijjar.endermanoverhaul.common.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.alexnijjar.endermanoverhaul.common.config.EndermanOverhaulConfig;
import tech.alexnijjar.endermanoverhaul.common.entities.base.BaseEnderman;
import tech.alexnijjar.endermanoverhaul.common.registry.ModParticleTypes;

public class SnowyEnderman extends BaseEnderman {

    private final EnchantedItemInUse fakeBootsItemInUse = new EnchantedItemInUse(ItemStack.EMPTY, EquipmentSlot.FEET, this);

    public SnowyEnderman(EntityType<? extends EnderMan> entityType, Level level) {
        super(entityType, level);
        xpReward = 8;
    }

    public static @NotNull AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH, 35.0)
            .add(Attributes.MOVEMENT_SPEED, 0.1944)
            .add(Attributes.ATTACK_DAMAGE, 6.0)
            .add(Attributes.FOLLOW_RANGE, 42.0);
    }

    public static boolean checkMonsterSpawnRules(@NotNull EntityType<? extends Monster> type, ServerLevelAccessor level, @NotNull MobSpawnType spawnType, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (!EndermanOverhaulConfig.spawnSnowyEnderman || !EndermanOverhaulConfig.allowSpawning) return false;
        return BaseEnderman.checkMonsterSpawnRules(type, level, spawnType, pos, random);
    }

    @Override
    public boolean canOpenMouth() {
        return false;
    }

    @Override
    public @Nullable ParticleOptions getCustomParticles() {
        return ModParticleTypes.SNOW.get();
    }

    @Override
    public double getVisionRange() {
        return 42.0;
    }

    @Override
    public @Nullable MobEffectInstance getHitEffect() {
        return new MobEffectInstance(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0));
    }

    @Override
    protected void onChangedBlock(ServerLevel level, BlockPos pos) {
        super.onChangedBlock(level, pos);
        level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).get(Enchantments.FROST_WALKER).runLocationChangedEffects(
            level,
            0,
            this.fakeBootsItemInUse,
            this
        );
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity target) {
        if (super.doHurtTarget(target)) {
            if (target instanceof LivingEntity entity && !entity.isFullyFrozen()) {
                entity.setTicksFrozen(entity.getTicksFrozen() + 100);
            }

            return true;
        } else {
            return false;
        }
    }
}
