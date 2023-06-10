package com.nitespring.bloodborne.common.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import com.google.common.collect.ImmutableList;
import com.nitespring.bloodborne.client.render.items.SpawnEggRenderer;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;



public class EntityModelSpawnEggItem extends SpawnEggItem{

	private static final List<EntityModelSpawnEggItem> EGGS = new ArrayList<>();

    private final Supplier<EntityType<?>> typeSupplier;

    @SuppressWarnings("deprecation")
	public EntityModelSpawnEggItem(Supplier<EntityType<?>> typeSupplier, int primaryColorIn, int secondaryColorIn, Properties builder)
    {
        super(null, primaryColorIn, secondaryColorIn, builder);
        this.typeSupplier = typeSupplier;
        EGGS.add(this);
    }

    @Override
    public EntityType<?> getType(CompoundTag tag)
    {
        return this.typeSupplier.get();
    }
    @Override
    protected EntityType<?> getDefaultType() {
	
    	return EntityType.ARMOR_STAND;
    }

    public static List<EntityModelSpawnEggItem> getSupplierEggs()
    {
        return ImmutableList.copyOf(EGGS);
    }
    @Mixin(SpawnEggItem.class)
    public interface SpawnEggItemMixin
    {
        @Accessor(value = "EGGS")
        static Map<EntityType<?>, SpawnEggItem> getEggMap() {
            throw new AssertionError();
        }
    }
    
	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		super.initializeClient(consumer);
		consumer.accept(new IClientItemExtensions() {
			private final BlockEntityWithoutLevelRenderer renderer = new SpawnEggRenderer<>();

			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return renderer;
			}
		});
	}
}