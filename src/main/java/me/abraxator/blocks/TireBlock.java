package me.abraxator.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class TireBlock extends Block {
    public static final IntegerProperty STACK = IntegerProperty.create("stack", 1, 4);
    public static final DirectionProperty FACING = BlockStateProperties.FACING
    public static final VoxelShape SHAPE1 = Block.box(0, 0, 0, 16, 3, 16);
    public static final VoxelShape SHAPE2 = Block.box(0, 0, 0, 16, 6, 16);
    public static final VoxelShape SHAPE3 = Block.box(0, 0, 0, 16, 9, 16);
    public static final VoxelShape SHAPE4 = Block.box(0, 0, 0, 16, 12, 16);

    public TireBlock(Properties pProperties) {
        super(pProperties);
        registerDefaultState(getStateDefinition().any().setValue(STACK, 1));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos blockPos = pContext.getClickedPos();
        BlockState blockState = pContext.getLevel().getBlockState(blockPos);

        if(blockState.is(this)){
            return pContext.getLevel().getBlockState(blockPos).setValue(STACK, Math.min(4, blockState.getValue(STACK) + 1));
        } else {
            return super.getStateForPlacement(pContext);
        }
    }

    @Override
    public boolean canBeHydrated(BlockState state, BlockGetter getter, BlockPos pos, FluidState fluid, BlockPos fluidPos) {
        return super.canBeHydrated(state, getter, pos, fluid, fluidPos);
    }

    @Override
    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        return pUseContext.getItemInHand().is(this.asItem()) && pState.getValue(STACK) < 4 ? true : super.canBeReplaced(pState, pUseContext);

    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(STACK)){
            case 1 -> SHAPE1;
            case 2 -> SHAPE2;
            case 3 -> SHAPE3;
            case 4 -> SHAPE4;
            default -> SHAPE1;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(STACK);
    }
}
