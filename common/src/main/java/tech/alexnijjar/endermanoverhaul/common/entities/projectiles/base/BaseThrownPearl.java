package tech.alexnijjar.endermanoverhaul.common.entities.projectiles.base;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BaseThrownPearl extends ThrowableItemProjectile {

    public BaseThrownPearl(EntityType<? extends ThrowableItemProjectile> type, Level level) {
        super(type, level);
    }

    public BaseThrownPearl(EntityType<? extends ThrowableItemProjectile> type, LivingEntity shooter, Level level) {
        super(type, shooter, level);
    }

    @Override
    public void tick() {
        if (this.getOwner() instanceof ServerPlayer player && !player.isAlive() && this.level().getGameRules().getBoolean(GameRules.RULE_ENDER_PEARLS_VANISH_ON_DEATH)) {
            this.discard();
        } else {
            super.tick();
        }
    }

    @Override
    public boolean canChangeDimensions(Level level, Level destinationLevel) {
        return level.dimension() == Level.END && this.getOwner() instanceof ServerPlayer serverplayer
            ? super.canChangeDimensions(level, destinationLevel) && serverplayer.seenCredits
            : super.canChangeDimensions(level, destinationLevel);
    }

    @Override
    protected void onInsideBlock(BlockState state) {
        super.onInsideBlock(state);
        if (state.is(Blocks.END_GATEWAY) && this.getOwner() instanceof ServerPlayer serverplayer) {
            serverplayer.onInsideBlock(state);
        }
    }
}