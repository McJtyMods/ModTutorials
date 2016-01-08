package mcjty.modtut.blocks.blinkingblock;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlinkingBlock extends Block implements ITileEntityProvider {

    public static final PropertyBool LIT = PropertyBool.create("lit");

    public BlinkingBlock() {
        super(Material.rock);
        setUnlocalizedName("blinkingblock");
        setRegistryName("blinkingblock");
        GameRegistry.registerBlock(this);
        GameRegistry.registerTileEntity(BlinkingTileEntity.class, "blinkingblock");
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public EnumWorldBlockLayer getBlockLayer() {
        return EnumWorldBlockLayer.TRANSLUCENT;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

//    @Override
//    public boolean isBlockNormalCube() {
//        return false;
//    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new BlinkingTileEntity();
    }

    private BlinkingTileEntity getTE(IBlockAccess world, BlockPos pos) {
        return (BlinkingTileEntity) world.getTileEntity(pos);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        return state.withProperty(LIT, getTE(world, pos).isLit());
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, LIT);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }
}
