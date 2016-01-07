package mcjty.modtut.blocks.isbmblock;

import mcjty.modtut.ModBlocks;
import mcjty.modtut.ModTut;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ISBMBlock extends Block {

    // Properties that indicate if there is the same block in a certain direction.
    public static final UnlistedPropertyBlockAvailable NORTH = new UnlistedPropertyBlockAvailable("north");
    public static final UnlistedPropertyBlockAvailable SOUTH = new UnlistedPropertyBlockAvailable("south");
    public static final UnlistedPropertyBlockAvailable WEST = new UnlistedPropertyBlockAvailable("west");
    public static final UnlistedPropertyBlockAvailable EAST = new UnlistedPropertyBlockAvailable("east");
    public static final UnlistedPropertyBlockAvailable UP = new UnlistedPropertyBlockAvailable("up");
    public static final UnlistedPropertyBlockAvailable DOWN = new UnlistedPropertyBlockAvailable("down");

    public ISBMBlock() {
        super(Material.rock);
        setUnlocalizedName("isbmblock");
        setRegistryName("isbmblock");
        GameRegistry.registerBlock(this);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        StateMapperBase ignoreState = new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState iBlockState) {
                return ExampleISBM.modelResourceLocation;
            }
        };
        ModelLoader.setCustomStateMapper(this, ignoreState);
    }

    @SideOnly(Side.CLIENT)
    public void initIsbm() {
        Item itemBlock = GameRegistry.findItem(ModTut.MODID, "isbmblock");
        ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(getRegistryName(), "inventory");
        final int DEFAULT_ITEM_SUBTYPE = 0;
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlock, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        world.markBlockRangeForRenderUpdate(pos.add(-1, -1, -1), pos.add(1, 1, 1));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public boolean isBlockNormalCube() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    protected BlockState createBlockState() {
        IProperty[] listedProperties = new IProperty[0]; // no listed properties
        IUnlistedProperty[] unlistedProperties = new IUnlistedProperty[] { NORTH, SOUTH, WEST, EAST, UP, DOWN };
        return new ExtendedBlockState(this, listedProperties, unlistedProperties);
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
        IExtendedBlockState extendedBlockState = (IExtendedBlockState) state;

        boolean north = isSameBlock(world, pos.north());
        boolean south = isSameBlock(world, pos.south());
        boolean west = isSameBlock(world, pos.west());
        boolean east = isSameBlock(world, pos.east());
        boolean up = isSameBlock(world, pos.up());
        boolean down = isSameBlock(world, pos.down());

        return extendedBlockState
                .withProperty(NORTH, north)
                .withProperty(SOUTH, south)
                .withProperty(WEST, west)
                .withProperty(EAST, east)
                .withProperty(UP, up)
                .withProperty(DOWN, down);
    }

    private boolean isSameBlock(IBlockAccess world, BlockPos pos) {
        return world.getBlockState(pos).getBlock() == ModBlocks.isbmBlock;
    }

}
