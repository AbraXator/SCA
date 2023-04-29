package me.abraxator.init;

import me.abraxator.SCA;
import me.abraxator.util.SCALoc;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SCA.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTab {
    public static CreativeModeTab TAB;

    @SubscribeEvent
    public static void onCreativeModeTabRegister(CreativeModeTabEvent.Register event) {
        TAB = event.registerCreativeModeTab(
                new SCALoc("sca_tab"), builder ->  builder
                        .icon(() -> new ItemStack(ModBlocks.TIRE.get()))
                        .displayItems((parameters, output) -> ModItems.ITEMS.getEntries().forEach(itemRegistryObject -> {
                            output.accept(itemRegistryObject.get());
                        }))
                        .title(Component.translatable("sca.tab"))
        );
    }
}
