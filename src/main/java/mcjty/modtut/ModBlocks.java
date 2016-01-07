package mcjty.modtut;

import mcjty.modtut.blocks.FirstBlock;
import mcjty.modtut.blocks.MultiTexturedBlock;
import mcjty.modtut.blocks.SimpleTexturedBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

    public static FirstBlock firstBlock;
    public static SimpleTexturedBlock simpleTexturedBlock;
    public static MultiTexturedBlock multiTexturedBlock;

    public static void init() {
        firstBlock = new FirstBlock();
        simpleTexturedBlock = new SimpleTexturedBlock();
        multiTexturedBlock = new MultiTexturedBlock();
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        simpleTexturedBlock.initModel();
        multiTexturedBlock.initModel();
    }

}
