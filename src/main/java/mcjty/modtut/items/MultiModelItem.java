package mcjty.modtut.items;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MultiModelItem extends Item {

    public MultiModelItem() {
        setRegistryName("multimodelitem");
        setUnlocalizedName("multimodelitem");
        GameRegistry.registerItem(this);
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
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            if (isBlue(stack)) {
                getTagCompoundSafe(stack).removeTag("blue");
            } else {
                getTagCompoundSafe(stack).setBoolean("blue", true);
            }
        }
        return stack;
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
