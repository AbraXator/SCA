package me.abraxator.init;

import me.abraxator.SCA;
import me.abraxator.blocks.EngineBlock;
import me.abraxator.blocks.TireBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, SCA.MOD_ID);

    public static final RegistryObject<Block> TIRE = registerBlock("tire", () -> new TireBlock(BlockBehaviour.Properties.of(Material.METAL)));
    public static final RegistryObject<Block> ENGINE = registerBlock("engine", () -> new EngineBlock(BlockBehaviour.Properties.of(Material.METAL)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));
    }
}
