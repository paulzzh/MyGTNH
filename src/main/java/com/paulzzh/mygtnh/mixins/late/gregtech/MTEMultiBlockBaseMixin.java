package com.paulzzh.mygtnh.mixins.late.gregtech;

import com.muxiu1997.sharewhereiam.network.MessageShareWaypoint;
import com.paulzzh.mygtnh.Utils;
import com.paulzzh.mygtnh.config.MyGTNHConfig;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.MTEMultiBlockBase;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.muxiu1997.sharewhereiam.network.NetworkHandler.network;

@Mixin(value = MTEMultiBlockBase.class)
public class MTEMultiBlockBaseMixin {
    @Redirect(method = "causeMaintenanceIssue", at = @At(value = "INVOKE", target = "Lgregtech/api/interfaces/tileentity/IGregTechTileEntity;getRandomNumber(I)I"), remap = false)
    public int getRandomNumber(IGregTechTileEntity instance, int i) throws UnsupportedEncodingException {
        int rand = instance.getRandomNumber(i);
        if (i == 6) {
            String name = instance.getMetaTileEntity().getLocalName();
            ChunkCoordinates c = instance.getCoords();
            int dimid = instance.getWorld().provider.dimensionId;
            String dim = Utils.getDimName(dimid);
            String tool = String.valueOf(rand);
            switch (rand) {
                case 0 -> tool = "扳手(Wrench)";
                case 1 -> tool = "螺丝刀(Screwdriver)";
                case 2 -> tool = "软锤(SoftHammer)";
                case 3 -> tool = "锻造锤(HardHammer)";
                case 4 -> tool = "电烙铁(SolderingTool)";
                case 5 -> tool = "撬棍(Crowbar)";
            }
            String msg = String.format("%s@%d,%d,%d,%s 产生维护问题:%s", name, c.posX, c.posY, c.posZ, dim, tool);
            MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(msg));
            MessageShareWaypoint wp = new MessageShareWaypoint();
            wp.playerName = String.format("多方块维护-%s", tool);
            wp.waypointJson = String.format("{\"id\":\"%s_-%d,%d,%d\",\"name\":\"%s\",\"icon\":\"waypoint-normal.png\",\"x\":%d,\"y\":%d,\"z\":%d,\"r\":255,\"g\":0,\"b\":0,\"enable\":true,\"type\":\"Normal\",\"origin\":\"JourneyMap\",\"dimensions\":[%d]}",
                name, c.posX, c.posY, c.posZ, name, c.posX, c.posY, c.posZ, dimid);
            wp.additionalInformation = "";
            network.sendToAll(wp);
            if (!MyGTNHConfig.multi_notify_url.equals("")) {
                new Utils.ThreadUrlPusher(MyGTNHConfig.multi_notify_url + URLEncoder.encode(msg, "utf8"));
            }
        }
        return rand;
    }
}
