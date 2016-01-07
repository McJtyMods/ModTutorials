package mcjty.modtut;

import mcjty.modtut.blocks.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

    public static FirstBlock firstBlock;
    public static SimpleTexturedBlock simpleTexturedBlock;
    public static MultiTexturedBlock multiTexturedBlock;
    public static StateTexturedBlock stateTexturedBlock;
    public static ModelBlock modelBlock;

    public static void init() {
        firstBlock = new FirstBlock();
        simpleTexturedBlock = new SimpleTexturedBlock();
        multiTexturedBlock = new MultiTexturedBlock();
        stateTexturedBlock = new StateTexturedBlock();
        modelBlock = new ModelBlock();
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        simpleTexturedBlock.initModel();
        multiTexturedBlock.initModel();
        stateTexturedBlock.initModel();
        modelBlock.initModel();
    }

}
