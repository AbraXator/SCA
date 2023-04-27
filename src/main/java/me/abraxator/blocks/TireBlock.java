package me.abraxator.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class TireBlock extends Block {
    public static final IntegerProperty STACK = IntegerProperty.create("stack", 1, 4);

    public TireBlock(Properties pProperties) {
        super(pProperties);
    }
}
