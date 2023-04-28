package me.abraxator.data;

import me.abraxator.SCA;
import me.abraxator.blocks.TireBlock;
import me.abraxator.init.ModBlocks;
import me.abraxator.util.SCALoc;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    final ExistingFileHelper existingFileHelper;

    public ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
        this.existingFileHelper = exFileHelper;
    }

    @Override
    protected void registerStatesAndModels() {
        this.getVariantBuilder(ModBlocks.TIRE.get())
                .partialState()
                .with(TireBlock.STACK, 1)
                .modelForState()
                .modelFile(tireFile(1))
                .addModel()
                .partialState()
                .with(TireBlock.STACK, 2)
                .modelForState()
                .modelFile(tireFile(2))
                .addModel()
                .partialState()
                .with(TireBlock.STACK, 3)
                .modelForState()
                .modelFile(tireFile(3))
                .addModel()
                .partialState()
                .with(TireBlock.STACK, 4)
                .modelForState()
                .modelFile(tireFile(4))
                .addModel();
        //horizontalBlock(ModBlocks.ENGINE.get(), new ModelFile.ExistingModelFile(new SCALoc("block/engine"), existingFileHelper));
    }

    private void block(Block block){
        this.getVariantBuilder(block)
                .partialState()
                .setModels(new ConfiguredModel(new ModelFile.ExistingModelFile(new SCALoc("block/engine"), existingFileHelper)));
    }

    private ModelFile.ExistingModelFile tireFile(int stack){
        return new ModelFile.ExistingModelFile(new SCALoc(modelFile(ModBlocks.TIRE.get()) + "_stack_" + stack), existingFileHelper);
    }

    private String modelFile(Block block){
        return "block/" + ModGenerators.trimmedId(block.getDescriptionId());
    }
}
