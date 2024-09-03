package tech.alexnijjar.endermanoverhaul;

import com.teamresourceful.resourcefulconfig.api.loader.Configurator;
import tech.alexnijjar.endermanoverhaul.common.config.EndermanOverhaulConfig;
import tech.alexnijjar.endermanoverhaul.common.registry.*;
import tech.alexnijjar.endermanoverhaul.common.network.NetworkHandler;

public class EndermanOverhaul {

    public static final String MOD_ID = "endermanoverhaul";
    public static final Configurator CONFIGURATOR = new Configurator(MOD_ID);

    public static void init() {
        CONFIGURATOR.register(EndermanOverhaulConfig.class);

        NetworkHandler.init();
        ModDataComponents.DATA_COMPONENT_TYPES.init();
        ModBlocks.BLOCKS.init();
        ModArmorMaterials.ARMOR_MATERIALS.init();
        ModItems.ITEMS.init();
        ModItems.TABS.init();
        ModEntityTypes.ENTITY_TYPES.init();
        ModParticleTypes.PARTICLE_TYPES.init();
        ModSoundEvents.SOUND_EVENTS.init();
    }

    public static void postInit() {
        ModEntityTypes.registerSpawnPlacements();
    }
}
