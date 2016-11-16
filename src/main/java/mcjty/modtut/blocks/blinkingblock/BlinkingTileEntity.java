package mcjty.modtut.blocks.blinkingblock;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

public class BlinkingTileEntity extends TileEntity implements ITickable {

    // We only calculate this value on the client so we don't care about the server
    // side and we also don't care about saving this value. That's why we have no
    // readFromNBT() and writeToNBT().
    private boolean lit = false;

    // Counter that is decremented faster if there are more entities in the vicinity.
    // If it reaches negative we toggle the light
    private int counter = 0;

    // To prevent counting entities every tick we delay it for 10 ticks and remember the last count we had.
    private int delayCounter = 10;
    private int lastCount = 0;

    public boolean isLit() {
        return lit;
    }

    @Override
    public void update() {
        // This method is called every tick (20 times per second normally)

        if (getWorld().isRemote) {
            // We only do something on the client so we don't have to bother about client-server communication.
            // The effect that we want to have (blinking the block) is client-side as well.
            updateCounter();
            counter -= lastCount * 3;
            if (counter <= 0) {
                lit = !lit;
                counter = 400;      // This is 20 seconds. Rate increases if more mods are near
                getWorld().markBlockRangeForRenderUpdate(getPos(), getPos());
            }
        }
    }

    private void updateCounter() {
        // Don't count the entities every tick. That would be a bit slow.
        delayCounter--;
        if (delayCounter <= 0) {
            List<EntityLivingBase> list = getWorld().getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(getPos().add(-10, -10, -10), getPos().add(10, 10, 10)));
            delayCounter = 10;
            lastCount = list.size();
        }
    }
}
