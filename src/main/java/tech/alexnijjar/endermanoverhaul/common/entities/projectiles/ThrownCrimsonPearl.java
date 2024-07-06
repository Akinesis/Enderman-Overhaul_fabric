package tech.alexnijjar.endermanoverhaul.common.entities.projectiles;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import tech.alexnijjar.endermanoverhaul.common.entities.projectiles.base.BaseThrownPearl;
import tech.alexnijjar.endermanoverhaul.common.registry.ModEntityTypes;
import tech.alexnijjar.endermanoverhaul.common.registry.ModItems;
import tech.alexnijjar.endermanoverhaul.common.utils.ModUtils;

public class ThrownCrimsonPearl extends BaseThrownPearl {
    public ThrownCrimsonPearl(EntityType<? extends ThrownCrimsonPearl> type, Level level) {
        super(type, level);
    }

    public ThrownCrimsonPearl(Level level, LivingEntity shooter) {
        super(ModEntityTypes.CRIMSON_PEARL.get(), shooter, level);
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return ModItems.CRIMSON_PEARL.get();
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        super.onHitEntity(result);
        result.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 0.0f);
    }

    @Override
    protected void onHit(@NotNull HitResult result) {
        super.onHit(result);

        if (this.level().isClientSide() || this.isRemoved()) return;
        for (int i = 0; i < 32; ++i) {
            ModUtils.sendParticles((ServerLevel) level(), ParticleTypes.PORTAL, this.getX(), (this.getY() - 1) + this.random.nextDouble() * 2.0, this.getZ(), 1, 0.0, 0.0, 0.0, -1.3);
        }

        if (!(getOwner() instanceof LivingEntity entity)) return;
        if (entity instanceof ServerPlayer serverPlayer) {
            if (this.random.nextFloat() < 0.05f && this.level().getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)) {
                Endermite endermite = EntityType.ENDERMITE.create(this.level());
                if (endermite != null) {
                    endermite.moveTo(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), entity.getXRot());
                    this.level().addFreshEntity(endermite);
                }
            }

            if (serverPlayer.connection.isAcceptingMessages() && serverPlayer.level() == this.level() && !serverPlayer.isSleeping()) {
                if (entity.isPassenger()) {
                    serverPlayer.dismountTo(this.getX(), this.getY(), this.getZ());
                } else {
                    entity.teleportTo(this.getX(), this.getY(), this.getZ());
                }

                entity.resetFallDistance();
                entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 1));
            }
        } else {
            entity.teleportTo(this.getX(), this.getY(), this.getZ());
            entity.resetFallDistance();
        }

        this.discard();
    }
}
