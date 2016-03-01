package mcjty.modtut;


import mcjty.modtut.input.InputHandler;
import mcjty.modtut.input.KeyBindings;
import mcjty.modtut.network.PacketHandler;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = ModTut.MODID, name = ModTut.MODNAME, version = ModTut.MODVERSION, dependencies = "required-after:Forge@[11.15.0.1684,)", useMetadata = true)
public class ModTut {

    public static final String MODID = "modtut";
    public static final String MODNAME = "Mod tutorials";
    public static final String MODVERSION = "0.0.1";

    @SidedProxy
    public static CommonProxy proxy;

    @Mod.Instance
    public static ModTut instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

    public static class CommonProxy {
        public void preInit(FMLPreInitializationEvent e) {
            // Initialize our packet handler. Make sure the name is
            // 20 characters or less!
            PacketHandler.registerMessages("modtut");

            // Initialization of blocks and items typically goes here:
            ModBlocks.init();
            ModItems.init();
        }

        public void init(FMLInitializationEvent e) {

        }

        public void postInit(FMLPostInitializationEvent e) {

        }
    }


    public static class ClientProxy extends CommonProxy {
        @Override
        public void preInit(FMLPreInitializationEvent e) {
            super.preInit(e);

            MinecraftForge.EVENT_BUS.register(new ClientEventHandlers());
            OBJLoader.instance.addDomain(MODID);

            // Typically initialization of models and such goes here:
            ModBlocks.initModels();
            ModItems.initModels();
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

    public static class ServerProxy extends CommonProxy {

    }
}
