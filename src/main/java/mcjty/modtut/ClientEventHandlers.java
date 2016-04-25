package mcjty.modtut;

import mcjty.modtut.blocks.bakedmodel.ExampleBakedModel;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientEventHandlers {

    @SubscribeEvent
    public void onModelBakeEvent(ModelBakeEvent event) {
        Object object =  event.getModelRegistry().getObject(ExampleBakedModel.modelResourceLocation);
        if (object != null) {
            ExampleBakedModel customModel = new ExampleBakedModel();
            event.getModelRegistry().putObject(ExampleBakedModel.modelResourceLocation, customModel);
        }
    }

}
