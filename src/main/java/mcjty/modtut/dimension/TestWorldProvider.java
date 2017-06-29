package mcjty.modtut.dimension;

import mcjty.modtut.ModDimensions;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;

public class TestWorldProvider extends WorldProvider {

    @Override
    public DimensionType getDimensionType() {
        return ModDimensions.testDimensionType;
    }

    @Override
    public String getSaveFolder() {
        return "TEST";
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new TestChunkGenerator(world);
    }
}
