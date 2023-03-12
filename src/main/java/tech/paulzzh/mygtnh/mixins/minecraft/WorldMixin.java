package tech.paulzzh.mygtnh.mixins.minecraft;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.paulzzh.mygtnh.mcmt.FJPool;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = World.class, priority = 1)
public abstract class WorldMixin implements IBlockAccess {
    @Shadow
    public List loadedTileEntityList;

    private List loadedTileEntityListbackup;
    @Shadow
    private List field_147483_b;

    @Inject(method = "updateEntities()V", at = @At(value = "CONSTANT", args = "stringValue=blockEntities"))
    public void head(CallbackInfo ci) throws InterruptedException {
        //MyGTNH.info(String.format("loadedTileEntityList: %d",loadedTileEntityList.size()));
        List blacklist = FJPool.TEsubmit(loadedTileEntityList);


        if (!field_147483_b.isEmpty()) {
            for (Object tile : field_147483_b) {
                ((TileEntity) tile).onChunkUnload();
            }
            loadedTileEntityList.removeAll(field_147483_b);
            field_147483_b.clear();
        }

        loadedTileEntityListbackup = new ArrayList(loadedTileEntityList);
        loadedTileEntityList.clear();
    }

    @Inject(method = "updateEntities()V", at = @At(value = "CONSTANT", args = "stringValue=pendingBlockEntities"))
    public void tail(CallbackInfo ci) {
        loadedTileEntityList = loadedTileEntityListbackup;
    }
}