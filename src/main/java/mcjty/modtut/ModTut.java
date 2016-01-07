package mcjty.gaia;


import mcjty.gaia.blocks.tank.TankNetwork;
import mcjty.gaia.environment.EnvironmentData;
import mcjty.gaia.hosemultiblock.HoseNetwork;
import mcjty.gaia.proxy.CommonProxy;
import mcjty.gaia.waila.WailaCompatibility;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod(modid = Gaia.MODID, name = Gaia.ModName, dependencies = "required-after:Forge@[11.15.0.1684,)", useMetadata = true)
public class Gaia {

    public static final String MODID = "gaia";
    public static final String ModName = "Gaia Realism";

    @SidedProxy(clientSide = "mcjty.gaia.proxy.ClientProxy", serverSide = "mcjty.gaia.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static Gaia instance;
    public static Logger logger;
    public static CreativeTabs creativeTab;
    public static Random random;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger = event.getModLog();
        creativeTab = new CreativeTabs("gaia") {
            @Override
            public Item getTabIconItem() {
                return Items.apple;
            }
        };
        random = new Random();
        proxy.preInit(event);

        WailaCompatibility.registerWaila();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

    @Mod.EventHandler
    public void serverStopped(FMLServerStoppedEvent event) {
        logger.log(Level.INFO, "Gaia: server is stopping. Shutting down gracefully");
        TankNetwork.clearInstance();
        HoseNetwork.clearInstance();
        EnvironmentData.clearInstance();
    }

    private static void registerForgeAndFML(Object o){
        registerForge(o);
    }

    private static void registerForge(Object o){
        MinecraftForge.EVENT_BUS.register(o);
    }

}
