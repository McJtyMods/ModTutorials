package mcjty.modtut;

import mcjty.modtut.blocks.*;
import mcjty.modtut.blocks.blinkingblock.BlinkingBlock;
import mcjty.modtut.blocks.datablock.DataBlock;
import mcjty.modtut.blocks.bakedmodel.BakedModelBlock;
import mcjty.modtut.blocks.itempedestal.PedestalBlock;
import mcjty.modtut.blocks.testcontainer.TestContainerBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

    public static FirstBlock firstBlock;
    public static SimpleTexturedBlock simpleTexturedBlock;
    public static MultiTexturedBlock multiTexturedBlock;
    public static StateTexturedBlock stateTexturedBlock;
    public static ModelBlock modelBlock;
    public static BakedModelBlock bakedModelBlock;
    public static DataBlock dataBlock;
    public static BlinkingBlock blinkingBlock;
    public static PedestalBlock pedestalBlock;
    public static TestContainerBlock testContainerBlock;

    public static void init() {
        firstBlock = new FirstBlock();
        simpleTexturedBlock = new SimpleTexturedBlock();
        multiTexturedBlock = new MultiTexturedBlock();
        stateTexturedBlock = new StateTexturedBlock();
        modelBlock = new ModelBlock();
        bakedModelBlock = new BakedModelBlock();
        dataBlock = new DataBlock();
        blinkingBlock = new BlinkingBlock();
        pedestalBlock = new PedestalBlock();
        testContainerBlock = new TestContainerBlock();
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        simpleTexturedBlock.initModel();
        multiTexturedBlock.initModel();
        stateTexturedBlock.initModel();
        modelBlock.initModel();
        bakedModelBlock.initModel();
        dataBlock.initModel();
        blinkingBlock.initModel();
        pedestalBlock.initModel();
        testContainerBlock.initModel();
    }

    @SideOnly(Side.CLIENT)
    public static void initItemModels() {
        bakedModelBlock.initItemModel();
    }
}
