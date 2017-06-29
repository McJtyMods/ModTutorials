package mcjty.modtut.items;

import mcjty.modtut.ModTut;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MultiModelItem extends Item {

    public MultiModelItem() {
        setRegistryName("multimodelitem");
        setUnlocalizedName(ModTut.MODID + ".multimodelitem");
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelResourceLocation blueModel = new ModelResourceLocation(getRegistryName() + "_blue", "inventory");
        ModelResourceLocation redModel = new ModelResourceLocation(getRegistryName() + "_red", "inventory");

        ModelBakery.registerItemVariants(this, blueModel, redModel);

        ModelLoader.setCustomMeshDefinition(this, stack -> {
            if (isBlue(stack)) {
                return blueModel;
            } else {
                return redModel;
            }
        });
    }

    private boolean isBlue(ItemStack stack) {
        return getTagCompoundSafe(stack).hasKey("blue");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer playerIn, EnumHand hand) {
        ItemStack stack = playerIn.getHeldItem(hand);
        if (!world.isRemote) {
            if (isBlue(stack)) {
                getTagCompoundSafe(stack).removeTag("blue");
            } else {
                getTagCompoundSafe(stack).setBoolean("blue", true);
            }
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    private NBTTagCompound getTagCompoundSafe(ItemStack stack) {
        NBTTagCompound tagCompound = stack.getTagCompound();
        if (tagCompound == null) {
            tagCompound = new NBTTagCompound();
            stack.setTagCompound(tagCompound);
        }
        return tagCompound;
    }
}
