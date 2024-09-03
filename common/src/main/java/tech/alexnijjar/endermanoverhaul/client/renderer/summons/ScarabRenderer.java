package tech.alexnijjar.endermanoverhaul.client.renderer.summons;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import tech.alexnijjar.endermanoverhaul.EndermanOverhaul;
import tech.alexnijjar.endermanoverhaul.common.entities.summons.Scarab;

public class ScarabRenderer extends GeoEntityRenderer<Scarab> {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "scarab/scarab");

    public ScarabRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<Scarab>(ResourceLocation.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "scarab"))
            .withAltTexture(TEXTURE)
            .withAltAnimations(ResourceLocation.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "scarab")));
    }
}
