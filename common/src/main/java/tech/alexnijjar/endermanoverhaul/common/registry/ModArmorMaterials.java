package tech.alexnijjar.endermanoverhaul.common.registry;

import com.teamresourceful.resourcefullib.common.registry.HolderRegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import tech.alexnijjar.endermanoverhaul.EndermanOverhaul;

import java.util.EnumMap;
import java.util.List;

public class ModArmorMaterials {
    public static final ResourcefulRegistry<ArmorMaterial> ARMOR_MATERIALS = ResourcefulRegistries.create(BuiltInRegistries.ARMOR_MATERIAL, EndermanOverhaul.MOD_ID);

    public static final HolderRegistryEntry<ArmorMaterial> HOOD = ARMOR_MATERIALS.registerHolder("hood", () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.BOOTS, 1);
        map.put(ArmorItem.Type.LEGGINGS, 4);
        map.put(ArmorItem.Type.CHESTPLATE, 5);
        map.put(ArmorItem.Type.HELMET, 2);
        map.put(ArmorItem.Type.BODY, 4);
    }),
        15,
        SoundEvents.ARMOR_EQUIP_LEATHER,
        () -> Ingredient.of(Items.LEATHER),
        List.of(new ArmorMaterial.Layer(ResourceLocation.withDefaultNamespace("chainmail"))),
        0,
        0
    ));
}

