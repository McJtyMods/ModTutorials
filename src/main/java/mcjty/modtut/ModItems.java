package mcjty.modtut;

import mcjty.modtut.items.FirstItem;
import mcjty.modtut.items.MultiModelItem;
import mcjty.modtut.items.SimpleTexturedItem;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

    @GameRegistry.ObjectHolder("modtut:firstitem")
    public static FirstItem firstItem;

    @GameRegistry.ObjectHolder("modtut:simpletextureditem")
    public static SimpleTexturedItem simpleTexturedItem;

    @GameRegistry.ObjectHolder("modtut:multimodelitem")
    public static MultiModelItem multiModelItem;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        simpleTexturedItem.initModel();
        multiModelItem.initModel();
    }

}
