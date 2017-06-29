package mcjty.modtut.proxy;

import mcjty.modtut.*;
import mcjty.modtut.blocks.*;
import mcjty.modtut.blocks.bakedmodel.BakedModelBlock;
import mcjty.modtut.blocks.blinkingblock.BlinkingBlock;
import mcjty.modtut.blocks.blinkingblock.BlinkingTileEntity;
import mcjty.modtut.blocks.datablock.DataBlock;
import mcjty.modtut.blocks.datablock.DataTileEntity;
import mcjty.modtut.blocks.itempedestal.PedestalBlock;
import mcjty.modtut.blocks.itempedestal.PedestalTileEntity;
import mcjty.modtut.blocks.testcontainer.TestContainerBlock;
import mcjty.modtut.blocks.testcontainer.TestContainerTileEntity;
import mcjty.modtut.compat.MainCompatHandler;
import mcjty.modtut.items.FirstItem;
import mcjty.modtut.items.MultiModelItem;
import mcjty.modtut.items.SimpleTexturedItem;
import mcjty.modtut.network.PacketHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;

@Mod.EventBusSubscriber
public class CommonProxy {

    // Config instance
    public static Configuration config;

    public void preInit(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "modtut.cfg"));
        Config.readConfig();

        // Initialize our packet handler. Make sure the name is
        // 20 characters or less!
        PacketHandler.registerMessages("modtut");

        // Initialization of blocks and items typically goes here:
        ModEntities.init();
        ModDimensions.init();

        MainCompatHandler.registerWaila();
        MainCompatHandler.registerTOP();

    }

    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(ModTut.instance, new GuiProxy());
    }

    public void postInit(FMLPostInitializationEvent e) {
        if (config.hasChanged()) {
            config.save();
        }
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new StateTexturedBlock());
        event.getRegistry().register(new BlinkingBlock());
        event.getRegistry().register(new FirstBlock());
        event.getRegistry().register(new SimpleTexturedBlock());
        event.getRegistry().register(new MultiTexturedBlock());
        event.getRegistry().register(new BakedModelBlock());
        event.getRegistry().register(new TestContainerBlock());
        event.getRegistry().register(new DataBlock());
        event.getRegistry().register(new ModelBlock());
        event.getRegistry().register(new PedestalBlock());

        GameRegistry.registerTileEntity(BlinkingTileEntity.class, ModTut.MODID + "_blinkingblock");
        GameRegistry.registerTileEntity(TestContainerTileEntity.class, ModTut.MODID + "_testcontainerblock");
        GameRegistry.registerTileEntity(DataTileEntity.class, ModTut.MODID + "_datablock");
        GameRegistry.registerTileEntity(PedestalTileEntity.class, ModTut.MODID + "_pedestalblock");
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new FirstItem());
        event.getRegistry().register(new SimpleTexturedItem());
        event.getRegistry().register(new MultiModelItem());

        event.getRegistry().register(new ItemBlock(ModBlocks.stateTexturedBlock).setRegistryName(ModBlocks.stateTexturedBlock.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.blinkingBlock).setRegistryName(ModBlocks.blinkingBlock.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.firstBlock).setRegistryName(ModBlocks.firstBlock.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.simpleTexturedBlock).setRegistryName(ModBlocks.simpleTexturedBlock.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.multiTexturedBlock).setRegistryName(ModBlocks.multiTexturedBlock.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.bakedModelBlock).setRegistryName(ModBlocks.bakedModelBlock.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.testContainerBlock).setRegistryName(ModBlocks.testContainerBlock.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.dataBlock).setRegistryName(ModBlocks.dataBlock.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.modelBlock).setRegistryName(ModBlocks.modelBlock.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.pedestalBlock).setRegistryName(ModBlocks.pedestalBlock.getRegistryName()));
    }

}
