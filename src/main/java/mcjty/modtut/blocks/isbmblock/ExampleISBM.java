package mcjty.modtut.blocks.isbmblock;

import com.google.common.primitives.Ints;
import mcjty.modtut.ModTut;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.model.ISmartBlockModel;
import net.minecraftforge.client.model.pipeline.LightUtil;
import net.minecraftforge.common.property.IExtendedBlockState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExampleISBM implements ISmartBlockModel {

    public static final ModelResourceLocation modelResourceLocation = new ModelResourceLocation(ModTut.MODID + ":isbmblock");

    @Override
    public IBakedModel handleBlockState(IBlockState state) {
        IExtendedBlockState extendedBlockState = (IExtendedBlockState) state;
        Boolean north = extendedBlockState.getValue(ISBMBlock.NORTH);
        Boolean south = extendedBlockState.getValue(ISBMBlock.SOUTH);
        Boolean west = extendedBlockState.getValue(ISBMBlock.WEST);
        Boolean east = extendedBlockState.getValue(ISBMBlock.EAST);
        Boolean up = extendedBlockState.getValue(ISBMBlock.UP);
        Boolean down = extendedBlockState.getValue(ISBMBlock.DOWN);
        return new BakedModel(north, south, west, east, up, down);
    }

    @Override
    public List<BakedQuad> getFaceQuads(EnumFacing side) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<BakedQuad> getGeneralQuads() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return null;
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return null;
    }

    public class BakedModel implements IBakedModel {
        private TextureAtlasSprite sprite;

        private final boolean north;
        private final boolean south;
        private final boolean west;
        private final boolean east;
        private final boolean up;
        private final boolean down;

        public BakedModel(boolean north, boolean south, boolean west, boolean east, boolean up, boolean down) {
            sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(ModTut.MODID + ":blocks/tank");
            this.north = north;
            this.south = south;
            this.west = west;
            this.east = east;
            this.up = up;
            this.down = down;
        }

        private int[] vertexToInts(float x, float y, float z, float u, float v) {
            return new int[] {
                    Float.floatToRawIntBits(x),
                    Float.floatToRawIntBits(y),
                    Float.floatToRawIntBits(z),
                    -1,
                    Float.floatToRawIntBits(sprite.getInterpolatedU(u)),
                    Float.floatToRawIntBits(sprite.getInterpolatedV(v)),
                    0
            };
        }

        private BakedQuad createQuad(Vec3 v1, Vec3 v2, Vec3 v3, Vec3 v4) {
            Vec3 normal = v1.subtract(v2).crossProduct(v3.subtract(v2));
            EnumFacing side = LightUtil.toSide((float) normal.xCoord, (float) normal.yCoord, (float) normal.zCoord);

            return new BakedQuad(Ints.concat(
                    vertexToInts((float) v1.xCoord, (float) v1.yCoord, (float) v1.zCoord, 0, 0),
                    vertexToInts((float) v2.xCoord, (float) v2.yCoord, (float) v2.zCoord, 0, 16),
                    vertexToInts((float) v3.xCoord, (float) v3.yCoord, (float) v3.zCoord, 16, 16),
                    vertexToInts((float) v4.xCoord, (float) v4.yCoord, (float) v4.zCoord, 16, 0)
            ), -1, side);
        }

        @Override
        public List<BakedQuad> getFaceQuads(EnumFacing side) {
            return Collections.emptyList();
        }

        @Override
        public List<BakedQuad> getGeneralQuads() {
            List<BakedQuad> quads = new ArrayList<>();
            double o = .3;
            Vec3 p000 = new Vec3(o, o, o);
            Vec3 p100 = new Vec3(1-o, o, o);
            Vec3 p001 = new Vec3(o, o, 1-o);
            Vec3 p101 = new Vec3(1-o, o, 1-o);
            Vec3 p010 = new Vec3(o, 1-o, o);
            Vec3 p110 = new Vec3(1-o, 1-o, o);
            Vec3 p011 = new Vec3(o, 1-o, 1-o);
            Vec3 p111 = new Vec3(1-o, 1-o, 1-o);

            quads.add(createQuad(p010, p110, p111, p011));

            return quads;
        }

        @Override
        public boolean isAmbientOcclusion() {
            return true;
        }

        @Override
        public boolean isGui3d() {
            return true;
        }

        @Override
        public boolean isBuiltInRenderer() {
            return false;
        }

        @Override
        public TextureAtlasSprite getParticleTexture() {
            return sprite;
        }

        @Override
        public ItemCameraTransforms getItemCameraTransforms() {
            return ItemCameraTransforms.DEFAULT;
        }
    }
}
