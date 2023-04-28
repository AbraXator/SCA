package me.abraxator.data;

import me.abraxator.SCA;
import me.abraxator.init.ModBlocks;
import me.abraxator.util.SCALoc;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;

public class ModItemModelProvider extends ItemModelProvider {
    final ExistingFileHelper existingFileHelper;

    public ModItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
        this.existingFileHelper = existingFileHelper;
    }

    @Override
    protected void registerModels() {
         this.getBuilder(ModBlocks.TIRE.getId().getPath())
                 .parent(new ModelFile.ExistingModelFile(new ResourceLocation("item/generated"), existingFileHelper))
                 .texture("layer0", new SCALoc("item/tire"));
        this.getBuilder(ModBlocks.ENGINE.getId().getPath())
                .parent(new ModelFile.ExistingModelFile(new SCALoc("block/engine"), existingFileHelper));
    }
}
