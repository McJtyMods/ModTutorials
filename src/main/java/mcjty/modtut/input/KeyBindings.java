package mcjty.modtut.input;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeyBindings {

    public static KeyBinding tutorialKey;

    public static void init() {
        tutorialKey = new KeyBinding("key.tutorial", Keyboard.KEY_T, "key.categories.modtut");
        ClientRegistry.registerKeyBinding(tutorialKey);
    }
}
