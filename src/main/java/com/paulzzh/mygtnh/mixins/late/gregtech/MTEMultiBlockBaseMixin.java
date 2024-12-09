package com.paulzzh.mygtnh.mixins.late.gregtech;

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

@Mixin(value = MTEMultiBlockBase.class)
public class MTEMultiBlockBaseMixin {
    @Redirect(method = "causeMaintenanceIssue", at = @At(value = "INVOKE", target = "Lgregtech/api/interfaces/tileentity/IGregTechTileEntity;getRandomNumber(I)I"), remap = false)
    public int getRandomNumber(IGregTechTileEntity instance, int i) throws UnsupportedEncodingException {
        int rand = instance.getRandomNumber(i);
        if (i == 6) {
            String name = instance.getMetaTileEntity().getLocalName();
            ChunkCoordinates c = instance.getCoords();
            String dim = Utils.getDimName(instance.getWorld().provider.dimensionId);
            String tool = String.valueOf(rand);
            switch (rand) {
                case 0 -> tool = "mWrench";
                case 1 -> tool = "mScrewdriver";
                case 2 -> tool = "mSoftHammer";
                case 3 -> tool = "mHardHammer";
                case 4 -> tool = "mSolderingTool";
                case 5 -> tool = "mCrowbar";
            }
            String msg = String.format("%s@%d,%d,%d,%s 产生维护问题:%s", name, c.posX, c.posY, c.posZ, dim, tool);
            MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(msg));
            if (!MyGTNHConfig.multi_notify_url.equals("")) {
                new Utils.ThreadUrlPusher(MyGTNHConfig.multi_notify_url + URLEncoder.encode(msg, "utf8"));
            }
        }
        return rand;
    }
}
