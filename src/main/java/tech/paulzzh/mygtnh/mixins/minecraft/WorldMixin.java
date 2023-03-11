package tech.paulzzh.mygtnh.mixins.minecraft;

import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.paulzzh.mygtnh.mcmt.FJPool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Mixin(value = World.class, priority = 1)
public abstract class WorldMixin implements IBlockAccess {
    @Shadow
    public List loadedTileEntityList;

    @Inject(method = "updateEntities()V", at = @At(value = "CONSTANT", args = "stringValue=blockEntities"))
    public void updateEntities(CallbackInfo ci) throws InterruptedException {
        //MyGTNH.info(String.format("loadedTileEntityList: %d",loadedTileEntityList.size()));
        FJPool.TEsubmit(loadedTileEntityList);
    }

    @ModifyVariable(method = "updateEntities()V", at = @At(value = "STORE", ordinal = 0))
    private Iterator injected(Iterator x) {
        //MyGTNH.info(String.format("updateEntities()Iterator: %d",loadedTileEntityList.size()));
        return (new ArrayList()).iterator();
    }
}