package mcjty.modtut;

import mcjty.modtut.blocks.isbmblock.ExampleISBM;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientEventHandlers {

    @SubscribeEvent
    public void onModelBakeEvent(ModelBakeEvent event) {
        Object object =  event.modelRegistry.getObject(ExampleISBM.modelResourceLocation);
        if (object != null) {
            ExampleISBM customModel = new ExampleISBM();
            event.modelRegistry.putObject(ExampleISBM.modelResourceLocation, customModel);
        }
    }

}
