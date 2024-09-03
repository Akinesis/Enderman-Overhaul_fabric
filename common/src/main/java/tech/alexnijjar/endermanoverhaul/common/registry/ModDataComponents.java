package tech.alexnijjar.endermanoverhaul.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import tech.alexnijjar.endermanoverhaul.EndermanOverhaul;

import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static final ResourcefulRegistry<DataComponentType<?>> DATA_COMPONENT_TYPES = ResourcefulRegistries.create(BuiltInRegistries.DATA_COMPONENT_TYPE, EndermanOverhaul.MOD_ID);

    // Should not be persistent as this stores the entity id which isn't persistent
    public static final RegistryEntry<DataComponentType<Integer>> BOUND_ENTITY = register("bound_entity", builder -> builder
        .networkSynchronized(ByteBufCodecs.VAR_INT)
    );

    private static <T> RegistryEntry<DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builder) {
        return DATA_COMPONENT_TYPES.register(name, () -> builder.apply(DataComponentType.builder()).build());
    }
}
