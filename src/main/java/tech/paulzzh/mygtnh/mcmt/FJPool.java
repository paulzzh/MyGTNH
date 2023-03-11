package tech.paulzzh.mygtnh.mcmt;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import tech.paulzzh.mygtnh.MyGTNH;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.atomic.AtomicInteger;

public class FJPool {

    private static ExecutorService TEupdatepool;

    public static void setupThreadPool(int count) {
        AtomicInteger tid = new AtomicInteger();
        final ClassLoader cl = MyGTNH.class.getClassLoader();

        ForkJoinPool.ForkJoinWorkerThreadFactory factory = p -> {
            ForkJoinWorkerThread fjwt = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(p);
            fjwt.setName("MCMT-Tick-Pool-Thread-" + tid.getAndIncrement());
            fjwt.setContextClassLoader(cl);
            return fjwt;
        };

        TEupdatepool = new ForkJoinPool(count, factory, null, false);
    }

    public static void TEsubmit(List loadedTileEntityList) throws InterruptedException {
        Iterator iterator = loadedTileEntityList.iterator();
        List<Callable<Void>> other = new ArrayList<>();
        List greg = new ArrayList();
        List mpart = new ArrayList();
        List ae = new ArrayList();
        List eio = new ArrayList();
        List oc = new ArrayList();
        List chest = new ArrayList();
        while (iterator.hasNext()) {
            TileEntity tileentity = (TileEntity) iterator.next();
            if (tileentity instanceof gregtech.api.metatileentity.BaseTileEntity) {
                greg.add(tileentity);
            } else if (tileentity instanceof codechicken.multipart.TileMultipart) {
                mpart.add(tileentity);
            } else if (tileentity instanceof appeng.tile.AEBaseTile || tileentity instanceof net.bdew.lib.tile.TileExtended) {
                ae.add(tileentity);
            } else if (tileentity instanceof com.enderio.core.common.TileEntityEnder) {
                eio.add(tileentity);
            } else if (tileentity instanceof li.cil.oc.common.tileentity.traits.TileEntity || tileentity instanceof shedar.mods.ic2.nuclearcontrol.tileentities.TileEntityInfoPanel || tileentity instanceof shedar.mods.ic2.nuclearcontrol.tileentities.TileEntityInfoPanelExtender) {
                oc.add(tileentity);
            } else if (tileentity instanceof cpw.mods.ironchest.TileEntityIronChest || tileentity instanceof codechicken.enderstorage.storage.item.TileEnderChest) {
                chest.add(tileentity);
            } else {
                other.add(() -> {
                    TEupdate(tileentity);
                    return null;
                });
            }
        }
        other.add(() -> {
            TEupdateSingle(greg);
            return null;
        });
        other.add(() -> {
            TEupdateSingle(mpart);
            return null;
        });
        other.add(() -> {
            TEupdateSingle(ae);
            return null;
        });
        other.add(() -> {
            TEupdateSingle(eio);
            return null;
        });
        other.add(() -> {
            TEupdateSingle(oc);
            return null;
        });
        TEupdatepool.invokeAll(other);
    }

    private static void TEupdateSingle(List loadedTileEntityList) {
        Iterator iterator = loadedTileEntityList.iterator();
        while (iterator.hasNext()) {
            TileEntity tileentity = (TileEntity) iterator.next();
            TEupdate(tileentity);
        }
    }

    private static void TEupdate(TileEntity tileentity) {
        World world = tileentity.getWorldObj();
        if (!tileentity.isInvalid() && tileentity.hasWorldObj() && world.blockExists(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord)) {
            try {
                tileentity.updateEntity();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //MyGTNH.info(String.format("TE updateEntity @ %d,%d,%d",tileentity.xCoord, tileentity.yCoord, tileentity.zCoord));
        }

        if (tileentity.isInvalid()) {

            if (world.getChunkProvider().chunkExists(tileentity.xCoord >> 4, tileentity.zCoord >> 4)) {
                Chunk chunk = world.getChunkFromChunkCoords(tileentity.xCoord >> 4, tileentity.zCoord >> 4);

                if (chunk != null) {
                    chunk.removeInvalidTileEntity(tileentity.xCoord & 15, tileentity.yCoord, tileentity.zCoord & 15);
                }
            }
        }
    }
}
