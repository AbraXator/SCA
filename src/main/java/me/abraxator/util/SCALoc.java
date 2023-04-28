package me.abraxator.util;

import me.abraxator.SCA;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class SCALoc extends ResourceLocation {
    public SCALoc(String pPath) {
        super(SCA.MOD_ID, pPath);
    }
}
