package mcjty.modtut.proxy;

import mcjty.modtut.*;
import mcjty.modtut.compat.MainCompatHandler;
import mcjty.modtut.network.PacketHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
        // Initialize our packet handler. Make sure the name is
        // 20 characters or less!
        PacketHandler.registerMessages("modtut");

        // Initialization of blocks and items typically goes here:
        ModBlocks.init();
        ModItems.init();
        ModEntities.init();
        ModDimensions.init();

        MainCompatHandler.registerWaila();
        MainCompatHandler.registerTOP();

    }

    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(ModTut.instance, new GuiProxy());
    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}
