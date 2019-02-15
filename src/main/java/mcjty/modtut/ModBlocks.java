package mcjty.modtut;

import mcjty.modtut.blocks.*;
import mcjty.modtut.blocks.bakedmodel.BakedModelBlock;
import mcjty.modtut.blocks.blinkingblock.BlinkingBlock;
import mcjty.modtut.blocks.datablock.DataBlock;
import mcjty.modtut.blocks.itempedestal.PedestalBlock;
import mcjty.modtut.blocks.testcontainer.TestContainerBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

    @GameRegistry.ObjectHolder("modtut:firstblock")
    public static FirstBlock firstBlock;
    
    @GameRegistry.ObjectHolder("modtut:myblock")
    public static MyBlock myblock;

    @GameRegistry.ObjectHolder("modtut:simpletexturedblock")
    public static SimpleTexturedBlock simpleTexturedBlock;

    @GameRegistry.ObjectHolder("modtut:multitexturedblock")
    public static MultiTexturedBlock multiTexturedBlock;

    @GameRegistry.ObjectHolder("modtut:modelblock")
    public static ModelBlock modelBlock;

    @GameRegistry.ObjectHolder("modtut:bakedmodelblock")
    public static BakedModelBlock bakedModelBlock;

    @GameRegistry.ObjectHolder("modtut:datablock")
    public static DataBlock dataBlock;

    @GameRegistry.ObjectHolder("modtut:blinkingblock")
    public static BlinkingBlock blinkingBlock;

    @GameRegistry.ObjectHolder("modtut:pedestalblock")
    public static PedestalBlock pedestalBlock;

    @GameRegistry.ObjectHolder("modtut:testcontainerblock")
    public static TestContainerBlock testContainerBlock;

    @GameRegistry.ObjectHolder("modtut:statetexturedblock")
    public static StateTexturedBlock stateTexturedBlock;

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
