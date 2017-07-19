package mcjty.modtut.network;


import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSendKey implements IMessage {
    private BlockPos blockPos;

    @Override
    public void fromBytes(ByteBuf buf) {
        // Encoding the position as a long is more efficient
        blockPos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        // Encoding the position as a long is more efficient
        buf.writeLong(blockPos.toLong());
    }

    public PacketSendKey() {
        // Calculate the position of the block we are looking at
        RayTraceResult result = Minecraft.getMinecraft().objectMouseOver;
        blockPos = result.getBlockPos();
    }

    public static class Handler implements IMessageHandler<PacketSendKey, IMessage> {
        @Override
        public IMessage onMessage(PacketSendKey message, MessageContext ctx) {
            // Always use a construct like this to actually handle your message. This ensures that
            // youre 'handle' code is run on the main Minecraft thread. 'onMessage' itself
            // is called on the networking thread so it is not safe to do a lot of things
            // here.
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketSendKey message, MessageContext ctx) {
            // This code is run on the server side. So you can do server-side calculations here
            EntityPlayerMP playerEntity = ctx.getServerHandler().player;
            World world = playerEntity.getEntityWorld();
            // Check if the block (chunk) is loaded to prevent abuse from a client
            // trying to overload a server by randomly loading chunks
            if (world.isBlockLoaded(message.blockPos)) {
                Block block = world.getBlockState(message.blockPos).getBlock();
                playerEntity.sendStatusMessage(new TextComponentString(TextFormatting.GREEN + "Hit block: " + block.getRegistryName()), false);
            }
        }
    }
}
