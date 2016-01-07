package mcjty.modtut.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FirstBlock extends Block {

    public FirstBlock() {
        super(Material.rock);
        setUnlocalizedName("firstblock");
        setRegistryName("firstblock");
        GameRegistry.registerBlock(this);
    }
}
