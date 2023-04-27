package me.abraxator.datagen;

import me.abraxator.SCA;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.data.event.GatherDataEvent;

@Mod.EventBusSubscriber(modid = SCA.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SCADatagen {

    @SubscribeEvent
    public static void onDatagen(GatherDataEvent event) {

    }
}
