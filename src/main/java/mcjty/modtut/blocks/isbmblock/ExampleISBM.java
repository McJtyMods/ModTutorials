package mcjty.modtut.blocks.isbmblock;

import com.google.common.primitives.Ints;
import mcjty.modtut.ModTut;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.model.pipeline.LightUtil;
import net.minecraftforge.common.property.IExtendedBlockState;

import java.util.ArrayList;
import java.util.List;

public class ExampleISBM implements IBakedModel {

    public static final ModelResourceLocation modelResourceLocation = new ModelResourceLocation(ModTut.MODID + ":isbmblock");

    private TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(ModTut.MODID + ":blocks/isbmtexture");

    private int[] vertexToInts(double x, double y, double z, float u, float v, TextureAtlasSprite sprite) {
        return new int[] {
                Float.floatToRawIntBits((float) x),
                Float.floatToRawIntBits((float) y),
                Float.floatToRawIntBits((float) z),
                -1,
                Float.floatToRawIntBits(sprite.getInterpolatedU(u)),
                Float.floatToRawIntBits(sprite.getInterpolatedV(v)),
                0
        };
    }

    private BakedQuad createQuad(Vec3d v1, Vec3d v2, Vec3d v3, Vec3d v4, TextureAtlasSprite sprite) {
        Vec3d normal = v1.subtract(v2).crossProduct(v3.subtract(v2));
        EnumFacing side = LightUtil.toSide((float) normal.xCoord, (float) normal.yCoord, (float) normal.zCoord);

        return new BakedQuad(Ints.concat(
                vertexToInts(v1.xCoord, v1.yCoord, v1.zCoord, 0, 0, sprite),
                vertexToInts(v2.xCoord, v2.yCoord, v2.zCoord, 0, 16, sprite),
                vertexToInts(v3.xCoord, v3.yCoord, v3.zCoord, 16, 16, sprite),
                vertexToInts(v4.xCoord, v4.yCoord, v4.zCoord, 16, 0, sprite)
        ), -1, side, sprite, true, DefaultVertexFormats.ITEM);
    }

    @Override
    public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
        IExtendedBlockState extendedBlockState = (IExtendedBlockState) state;
        Boolean north = extendedBlockState.getValue(ISBMBlock.NORTH);
        Boolean south = extendedBlockState.getValue(ISBMBlock.SOUTH);
        Boolean west = extendedBlockState.getValue(ISBMBlock.WEST);
        Boolean east = extendedBlockState.getValue(ISBMBlock.EAST);
        Boolean up = extendedBlockState.getValue(ISBMBlock.UP);
        Boolean down = extendedBlockState.getValue(ISBMBlock.DOWN);
        List<BakedQuad> quads = new ArrayList<>();
        double o = .4;

        getParticleTexture();

        // For each side we either cap it off if there is no similar block adjacent on that side
        // or else we extend so that we touch the adjacent block:

        if (up) {
            quads.add(createQuad(new Vec3d(1-o, 1-o, o), new Vec3d(1-o, 1, o), new Vec3d(1-o, 1, 1-o), new Vec3d(1-o, 1-o, 1-o), sprite));
            quads.add(createQuad(new Vec3d(o, 1-o, 1-o), new Vec3d(o, 1, 1-o), new Vec3d(o, 1, o), new Vec3d(o, 1-o, o), sprite));
            quads.add(createQuad(new Vec3d(o, 1, o), new Vec3d(1-o, 1, o), new Vec3d(1-o, 1-o, o), new Vec3d(o, 1-o, o), sprite));
            quads.add(createQuad(new Vec3d(o, 1-o, 1-o), new Vec3d(1-o, 1-o, 1-o), new Vec3d(1-o, 1, 1-o), new Vec3d(o, 1, 1-o), sprite));
        } else {
            quads.add(createQuad(new Vec3d(o, 1-o, 1-o), new Vec3d(1-o, 1-o, 1-o), new Vec3d(1-o, 1-o, o), new Vec3d(o, 1-o, o), sprite));
        }

        if (down) {
            quads.add(createQuad(new Vec3d(1-o, 0, o), new Vec3d(1-o, o, o), new Vec3d(1-o, o, 1-o), new Vec3d(1-o, 0, 1-o), sprite));
            quads.add(createQuad(new Vec3d(o, 0, 1-o), new Vec3d(o, o, 1-o), new Vec3d(o, o, o), new Vec3d(o, 0, o), sprite));
            quads.add(createQuad(new Vec3d(o, o, o), new Vec3d(1-o, o, o), new Vec3d(1-o, 0, o), new Vec3d(o, 0, o), sprite));
            quads.add(createQuad(new Vec3d(o, 0, 1-o), new Vec3d(1-o, 0, 1-o), new Vec3d(1-o, o, 1-o), new Vec3d(o, o, 1-o), sprite));
        } else {
            quads.add(createQuad(new Vec3d(o, o, o), new Vec3d(1-o, o, o), new Vec3d(1-o, o, 1-o), new Vec3d(o, o, 1-o), sprite));
        }

        if (east) {
            quads.add(createQuad(new Vec3d(1-o, 1-o, 1-o), new Vec3d(1, 1-o, 1-o), new Vec3d(1, 1-o, o), new Vec3d(1-o, 1-o, o), sprite));
            quads.add(createQuad(new Vec3d(1-o, o, o), new Vec3d(1, o, o), new Vec3d(1, o, 1-o), new Vec3d(1-o, o, 1-o), sprite));
            quads.add(createQuad(new Vec3d(1-o, 1-o, o), new Vec3d(1, 1-o, o), new Vec3d(1, o, o), new Vec3d(1-o, o, o), sprite));
            quads.add(createQuad(new Vec3d(1-o, o, 1-o), new Vec3d(1, o, 1-o), new Vec3d(1, 1-o, 1-o), new Vec3d(1-o, 1-o, 1-o), sprite));
        } else {
            quads.add(createQuad(new Vec3d(1-o, o, o), new Vec3d(1-o, 1-o, o), new Vec3d(1-o, 1-o, 1-o), new Vec3d(1-o, o, 1-o), sprite));
        }

        if (west) {
            quads.add(createQuad(new Vec3d(0, 1-o, 1-o), new Vec3d(o, 1-o, 1-o), new Vec3d(o, 1-o, o), new Vec3d(0, 1-o, o), sprite));
            quads.add(createQuad(new Vec3d(0, o, o), new Vec3d(o, o, o), new Vec3d(o, o, 1-o), new Vec3d(0, o, 1-o), sprite));
            quads.add(createQuad(new Vec3d(0, 1-o, o), new Vec3d(o, 1-o, o), new Vec3d(o, o, o), new Vec3d(0, o, o), sprite));
            quads.add(createQuad(new Vec3d(0, o, 1-o), new Vec3d(o, o, 1-o), new Vec3d(o, 1-o, 1-o), new Vec3d(0, 1-o, 1-o), sprite));
        } else {
            quads.add(createQuad(new Vec3d(o, o, 1-o), new Vec3d(o, 1-o, 1-o), new Vec3d(o, 1-o, o), new Vec3d(o, o, o), sprite));
        }

        if (north) {
            quads.add(createQuad(new Vec3d(o, 1-o, o), new Vec3d(1-o, 1-o, o), new Vec3d(1-o, 1-o, 0), new Vec3d(o, 1-o, 0), sprite));
            quads.add(createQuad(new Vec3d(o, o, 0), new Vec3d(1-o, o, 0), new Vec3d(1-o, o, o), new Vec3d(o, o, o), sprite));
            quads.add(createQuad(new Vec3d(1-o, o, 0), new Vec3d(1-o, 1-o, 0), new Vec3d(1-o, 1-o, o), new Vec3d(1-o, o, o), sprite));
            quads.add(createQuad(new Vec3d(o, o, o), new Vec3d(o, 1-o, o), new Vec3d(o, 1-o, 0), new Vec3d(o, o, 0), sprite));
        } else {
            quads.add(createQuad(new Vec3d(o, 1-o, o), new Vec3d(1-o, 1-o, o), new Vec3d(1-o, o, o), new Vec3d(o, o, o), sprite));
        }
        if (south) {
            quads.add(createQuad(new Vec3d(o, 1-o, 1), new Vec3d(1-o, 1-o, 1), new Vec3d(1-o, 1-o, 1-o), new Vec3d(o, 1-o, 1-o), sprite));
            quads.add(createQuad(new Vec3d(o, o, 1-o), new Vec3d(1-o, o, 1-o), new Vec3d(1-o, o, 1), new Vec3d(o, o, 1), sprite));
            quads.add(createQuad(new Vec3d(1-o, o, 1-o), new Vec3d(1-o, 1-o, 1-o), new Vec3d(1-o, 1-o, 1), new Vec3d(1-o, o, 1), sprite));
            quads.add(createQuad(new Vec3d(o, o, 1), new Vec3d(o, 1-o, 1), new Vec3d(o, 1-o, 1-o), new Vec3d(o, o, 1-o), sprite));
        } else {
            quads.add(createQuad(new Vec3d(o, o, 1-o), new Vec3d(1-o, o, 1-o), new Vec3d(1-o, 1-o, 1-o), new Vec3d(o, 1-o, 1-o), sprite));
        }

        return quads;
    }

    @Override
    public ItemOverrideList getOverrides() {
        return null;
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
        if (sprite == null) {
            sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(ModTut.MODID + ":blocks/isbmtexture");
        }
        return sprite;
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
            return ItemCameraTransforms.DEFAULT;
        }
}
