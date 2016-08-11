package mcjty.modtut;

import mcjty.modtut.dimension.TestWorldProvider;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class ModDimensions {

    public static DimensionType testDimensionType;

    public static int DIMENSION_ID = 100;   // Do NOT hardcode dimension id in your own mods! Use a config!

    public static void init() {
        registerDimensionTypes();
        registerDimensions();
    }

    private static void registerDimensionTypes() {
        testDimensionType = DimensionType.register(ModTut.MODID, "_test", DIMENSION_ID, TestWorldProvider.class, false);
    }

    private static void registerDimensions() {
        DimensionManager.registerDimension(DIMENSION_ID, testDimensionType);
    }

}
