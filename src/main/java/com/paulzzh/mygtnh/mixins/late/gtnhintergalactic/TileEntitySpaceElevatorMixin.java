package com.paulzzh.mygtnh.mixins.late.gtnhintergalactic;

import com.gtnewhorizons.gtnhintergalactic.tile.multi.elevator.TileEntitySpaceElevator;
import com.gtnewhorizons.modularui.api.screen.ModularWindow;
import com.gtnewhorizons.modularui.api.widget.IWidgetBuilder;
import com.gtnewhorizons.modularui.api.widget.Widget;
import com.gtnewhorizons.modularui.common.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(value = TileEntitySpaceElevator.class)
public abstract class TileEntitySpaceElevatorMixin {

    @Redirect(method = "addUIWidgets", at = @At(value = "INVOKE", target = "Lcom/gtnewhorizons/modularui/api/screen/ModularWindow$Builder;widget(Lcom/gtnewhorizons/modularui/api/widget/Widget;)Lcom/gtnewhorizons/modularui/api/widget/IWidgetBuilder;", remap = false), remap = false)
    private IWidgetBuilder inject1(ModularWindow.Builder instance, Widget widget) {
        if (widget.getPos().getX() == 174) {
            ((ButtonWidget) widget).setOnClick((c, w) -> {
            }).setEnabled(false);
        }
        return instance.widget(widget);
    }
}
