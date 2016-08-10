package mcjty.modtut.entity;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderWeirdZombie extends RenderLiving<EntityWeirdZombie> {

    private ResourceLocation mobTexture = new ResourceLocation("modtut:textures/entity/weirdzombie.png");

    public static final Factory FACTORY = new Factory();

    public RenderWeirdZombie(RenderManager rendermanagerIn) {
        // We use the vanilla zombie model here and we simply
        // retexture it. Of course you can make your own model
        super(rendermanagerIn, new ModelZombie(), 0.5F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityWeirdZombie entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityWeirdZombie> {

        @Override
        public Render<? super EntityWeirdZombie> createRenderFor(RenderManager manager) {
            return new RenderWeirdZombie(manager);
        }

    }

}
