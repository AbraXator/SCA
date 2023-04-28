package me.abraxator.data;

import me.abraxator.SCA;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static me.abraxator.SCA.MOD_ID;

@Mod.EventBusSubscriber(modid = SCA.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModGenerators {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        PackOutput output = generator.getPackOutput();
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(output, MOD_ID, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(output, MOD_ID, existingFileHelper));
    }

    public static String trimmedId(String id){
        return id.replace("sca.", "").replace("block.", "");
    }
}
