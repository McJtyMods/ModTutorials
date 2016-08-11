package mcjty.modtut.dimension;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class TestChunkGenerator implements IChunkGenerator {

    private final World worldObj;
    private Random random;
    private Biome[] biomesForGeneration;

//    private List<Biome.SpawnListEntry> mobs = Lists.newArrayList(new Biome.SpawnListEntry(EntityDirtZombie.class, 100, 2, 2));

//    private MapGenBase caveGenerator = new MapGenCaves();
    private NormalTerrainGenerator terraingen = new NormalTerrainGenerator();

    public TestChunkGenerator(World worldObj) {
        this.worldObj = worldObj;
        long seed = worldObj.getSeed();
        this.random = new Random((seed + 516) * 314);
        terraingen.setup(worldObj, random);
//        caveGenerator = TerrainGen.getModdedMapGen(caveGenerator, CAVE);
    }

    @Override
    public Chunk provideChunk(int x, int z) {
        ChunkPrimer chunkprimer = new ChunkPrimer();

        this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
        terraingen.setBiomesForGeneration(biomesForGeneration);
        terraingen.generate(x, z, chunkprimer);
        this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
        terraingen.replaceBiomeBlocks(x, z, chunkprimer, this, biomesForGeneration);

//        this.caveGenerator.generate(this.worldObj, x, z, chunkprimer);

        Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);

        byte[] biomeArray = chunk.getBiomeArray();
        for (int i = 0; i < biomeArray.length; ++i) {
            biomeArray[i] = (byte)Biome.getIdForBiome(this.biomesForGeneration[i]);
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public void populate(int x, int z) {

    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
//        if (creatureType == EnumCreatureType.MONSTER){
//            return mobs;
//        }
        return ImmutableList.of();

    }

    @Nullable
    @Override
    public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position) {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {

    }
}
