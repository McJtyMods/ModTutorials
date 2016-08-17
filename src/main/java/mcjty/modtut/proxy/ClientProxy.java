package mcjty.modtut.proxy;

import mcjty.modtut.ModBlocks;
import mcjty.modtut.ModEntities;
import mcjty.modtut.ModItems;
import mcjty.modtut.ModTut;
import mcjty.modtut.blocks.bakedmodel.BakedModelLoader;
import mcjty.modtut.input.InputHandler;
import mcjty.modtut.input.KeyBindings;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);

        OBJLoader.INSTANCE.addDomain(ModTut.MODID);
        ModelLoaderRegistry.registerLoader(new BakedModelLoader());

        // Typically initialization of models and such goes here:
        ModBlocks.initModels();
        ModItems.initModels();
        ModEntities.initModels();
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);

        // Initialize our input handler so we can listen to keys
        MinecraftForge.EVENT_BUS.register(new InputHandler());
        KeyBindings.init();

        ModBlocks.initItemModels();
    }
}
