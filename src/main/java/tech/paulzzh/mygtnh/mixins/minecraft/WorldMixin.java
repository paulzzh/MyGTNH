package tech.paulzzh.mygtnh.mixins.minecraft;

import net.minecraft.block.Block;
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
import java.util.concurrent.ExecutionException;

@Mixin(value = World.class, priority = 1)
public abstract class WorldMixin implements IBlockAccess {
    @Shadow
    public List loadedTileEntityList;

    private List loadedTileEntityListbackup;
    @Shadow
    private List field_147483_b;

    @Inject(method = "updateEntities()V", at = @At(value = "CONSTANT", args = "stringValue=blockEntities"))
    public void head(CallbackInfo ci) throws InterruptedException, ExecutionException {
        //MyGTNH.info(String.format("Ticking #%d",MinecraftServer.getServer().getTickCounter()));
        World world = (World) (Object) this;
        FJPool.TEsubmit(world, loadedTileEntityList);

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

    @Inject(method = "notifyBlockOfNeighborChange", at = @At("HEAD"), cancellable = true)
    public void head2(int p_147460_1_, int p_147460_2_, int p_147460_3_, Block p_147460_4_, CallbackInfo ci) {
        World world = (World) (Object) this;
        Block block = world.getBlock(p_147460_1_, p_147460_2_, p_147460_3_);
        FJPool.NBsubmit(() -> block.onNeighborBlockChange(world, p_147460_1_, p_147460_2_, p_147460_3_, p_147460_4_));
        //MyGTNH.info("notifyBlockOfNeighborChange");
        ci.cancel();
    }
}