package tech.alexnijjar.endermanoverhaul.client.renderer.items;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import tech.alexnijjar.endermanoverhaul.EndermanOverhaul;
import tech.alexnijjar.endermanoverhaul.common.items.HoodItem;

public class HoodRenderer extends GeoArmorRenderer<HoodItem> {
    public HoodRenderer(Item item) {
        super(new DefaultedItemGeoModel<HoodItem>(BuiltInRegistries.ITEM.getKey(item))
            .withAltTexture(ResourceLocation.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "armor/" + BuiltInRegistries.ITEM.getKey(item).getPath())));
    }

    @Override
    protected void applyBoneVisibilityBySlot(EquipmentSlot currentSlot) {
        setAllVisible(false);

        if (EquipmentSlot.CHEST == currentSlot) {
            setBoneVisible(this.getHeadBone(this.model), true);
            setBoneVisible(this.getBodyBone(this.model), true);
            setBoneVisible(this.getRightArmBone(this.model), true);
            setBoneVisible(this.getLeftArmBone(this.model), true);
        }
    }
}
