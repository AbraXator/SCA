package me.abraxator.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
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

import java.util.List;
import java.util.stream.Stream;

public class TireBlock extends Block {
    public static final IntegerProperty STACK = IntegerProperty.create("stack", 1, 4);
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final VoxelShape SHAPE1 = Block.box(0, 0.001, 0, 16, 3, 16);
    public static final VoxelShape SHAPE2 = Block.box(0, 0.001, 0, 16, 6, 16);
    public static final VoxelShape SHAPE3 = Block.box(0, 0.001, 0, 16, 9, 16);
    public static final VoxelShape SHAPE4 = Block.box(0, 0.001, 0, 16, 12, 16);
    public static final VoxelShape EAST = Stream.of(
            Block.box(13.25, 0, 4, 16.25, 16, 12),
            Block.box(13.25, 0, 0, 16.25, 16, 16),
            Block.box(13.25, 4, 0, 16.25, 12, 16),
            Block.box(13.25, 2, 1, 16.25, 14, 15),
            Block.box(13.25, 1, 2, 16.25, 15, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(4, 0, 13.25, 12, 16, 16.25),
            Block.box(0, 0, 13.25, 16, 16, 16.25),
            Block.box(0, 4, 13.25, 16, 12, 16.25),
            Block.box(1, 2, 13.25, 15, 14, 16.25),
            Block.box(2, 1, 13.25, 14, 15, 16.25)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(0, 0, 4, 3, 16, 12),
            Block.box(0, 0, 0, 3, 16, 16),
            Block.box(0, 4, 0, 3, 12, 16),
            Block.box(0, 2, 1, 3, 14, 15),
            Block.box(0, 1, 2, 3, 15, 14)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape NORTH = Stream.of(
            Block.box(4, 0, 0, 12, 16, 3),
            Block.box(0, 0, 0, 16, 16, 3),
            Block.box(0, 4, 0, 16, 12, 3),
            Block.box(1, 2, 0, 15, 14, 3),
            Block.box(2, 1, 0, 14, 15, 3)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public TireBlock(Properties pProperties) {
        super(pProperties);
        registerDefaultState(getStateDefinition().any().setValue(STACK, 1));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos blockPos = pContext.getClickedPos();
        BlockState blockState = pContext.getLevel().getBlockState(blockPos);

        if(blockState.is(this) && isDownNUp(blockState)){
            return pContext.getLevel().getBlockState(blockPos).setValue(STACK, Math.min(4, blockState.getValue(STACK) + 1));
        } else {
            return defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
        }
    }

    @Override
    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        return isDownNUp(pState) && pUseContext.getItemInHand().is(this.asItem()) && pState.getValue(STACK) < 4 || super.canBeReplaced(pState, pUseContext);

    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor level, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if(isDownNUp(pState)){
            return switch (pState.getValue(STACK)) {
                case 1 -> SHAPE1;
                case 2 -> SHAPE2;
                case 3 -> SHAPE3;
                case 4 -> SHAPE4;
                default -> SHAPE1;
            };
        }else {
            return switch (pState.getValue(FACING)) {
                case DOWN, UP -> SHAPE1;
                case EAST -> EAST;
                case NORTH -> NORTH;
                case SOUTH -> SOUTH;
                case WEST -> WEST;
            };
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(STACK, FACING);
    }

    public static boolean isDownNUp(BlockState blockState){
        return List.of(Direction.DOWN, Direction.UP).contains(blockState.getValue(FACING));
    }
}
