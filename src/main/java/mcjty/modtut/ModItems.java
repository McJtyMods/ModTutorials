package mcjty.modtut;

import mcjty.modtut.items.FirstItem;
import mcjty.modtut.items.MultiModelItem;
import mcjty.modtut.items.SimpleTexturedItem;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

    public static FirstItem firstItem;
    public static SimpleTexturedItem simpleTexturedItem;
    public static MultiModelItem multiModelItem;

    public static void init() {
        firstItem = new FirstItem();
        simpleTexturedItem = new SimpleTexturedItem();
        multiModelItem = new MultiModelItem();
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        simpleTexturedItem.initModel();
        multiModelItem.initModel();
    }

}
