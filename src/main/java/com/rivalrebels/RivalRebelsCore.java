package com.rivalrebels;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import javax.annotation.Nullable;
import java.util.Map;

@IFMLLoadingPlugin.Name(value = ModInfo.name)
@IFMLLoadingPlugin.MCVersion(value = "1.12.2")
@IFMLLoadingPlugin.SortingIndex(value = 1001)
@IFMLLoadingPlugin.TransformerExclusions(value = {ModInfo.packagename})
public class RivalRebelsCore implements IFMLLoadingPlugin {
    @Override
    public String[] getASMTransformerClass() {
        return new String[] {ASMTransformer.class.getName()};
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
