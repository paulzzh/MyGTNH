package tech.paulzzh.mygtnh.mixins.appeng;

import appeng.api.implementations.IUpgradeableHost;
import appeng.api.networking.ticking.TickRateModulation;
import appeng.parts.automation.PartExportBus;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tech.paulzzh.mygtnh.MyGTNH;

@Mixin(value = PartExportBus.class, remap = false)
public abstract class PartExportBusMixin implements IUpgradeableHost {
    @Inject(method = "doBusWork", at = @At(value = "INVOKE_ASSIGN", target = "Lappeng/helpers/MultiCraftingTracker;handleCrafting(IJLappeng/api/storage/data/IAEItemStack;Lappeng/util/InventoryAdaptor;Lnet/minecraft/world/World;Lappeng/api/networking/IGrid;Lappeng/api/networking/crafting/ICraftingGrid;Lappeng/api/networking/security/BaseActionSource;)Z"))
    private void doBusWork(CallbackInfoReturnable<TickRateModulation> cir) {
        TileEntity tile = this.getTile();
        MyGTNH.info(String.format("ExportBus @ %d,%d,%d", tile.xCoord, tile.yCoord, tile.zCoord));
    }
}
