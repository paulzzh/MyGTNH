package com.paulzzh.mygtnh.mixins.late.gtnhintergalactic;

import com.gtnewhorizons.modularui.api.screen.ModularWindow;
import com.paulzzh.mygtnh.MyGTNH;
import gtnhintergalactic.tile.multi.elevator.TileEntitySpaceElevator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;


@Mixin(value = TileEntitySpaceElevator.class)
public abstract class TileEntitySpaceElevatorMixin {
    /**
     * @author Paulzzh
     * @reason remove tp button
     */
    @Overwrite(remap = false)
    private void addTeleportationButton(ModularWindow.Builder builder) {
        MyGTNH.LOG.info("remove space elevator tp button");
    }
}
