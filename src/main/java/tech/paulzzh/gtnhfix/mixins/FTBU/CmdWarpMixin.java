package tech.paulzzh.gtnhfix.mixins.FTBU;

import ftb.lib.BlockDimPos;
import ftb.utils.api.guide.GuidePage;
import ftb.utils.mod.cmd.CmdWarp;
import ftb.utils.world.LMWorldServer;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.paulzzh.gtnhfix.DimNameUtils;

import static net.minecraft.command.CommandBase.getCommandSenderAsPlayer;


@Mixin(value = CmdWarp.class, remap = false)
public class CmdWarpMixin {
    @Inject(method = "onCommand", at = @At("HEAD"), cancellable = true)
    private void onCommand(ICommandSender ics, String[] args, CallbackInfoReturnable<IChatComponent> cir) throws CommandException {
        if (args.length == 0) {
            GuidePage page;
            EntityPlayerMP ep = getCommandSenderAsPlayer(ics);
            GuidePage file = new GuidePage("server_info").setTitle(new ChatComponentText("Warps"));
            for (String w : LMWorldServer.inst.warps.list()) {
                BlockDimPos p = LMWorldServer.inst.warps.get(w);
                page = file.getSub(DimNameUtils.getDimName(p.dim));
                IChatComponent chatComponent = new ChatComponentText(w);
                ChatStyle chatStyle = new ChatStyle();
                chatStyle.setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(">>点击传送<< " + p.toString())));
                chatStyle.setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "warp " + w));
                chatComponent.setChatStyle(chatStyle);
                page.println(chatComponent);
            }
            file.displayGuide(ep);
            cir.setReturnValue(null);
        }
    }
}
