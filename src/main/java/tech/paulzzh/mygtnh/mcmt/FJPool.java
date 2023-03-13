package tech.paulzzh.mygtnh.mcmt;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import tech.paulzzh.mygtnh.MyGTNH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class FJPool {

    public static ReentrantLock AE2_NetworkMonitor_postChange = new ReentrantLock();
    public static ReentrantLock AE2_NetworkMonitor_injectItems = new ReentrantLock();
    public static ReentrantLock AE2_NetworkMonitor_extractItems = new ReentrantLock();
    public static ReentrantLock AE2_GridStorageCache_cellUpdate = new ReentrantLock();
    public static ReentrantLock PR_WirePropagator_propagateTo = new ReentrantLock();
    public static ReentrantLock MC_World_notifyBlockOfNeighborChange = new ReentrantLock();
    public static ReentrantLock MC_WorldServer_tickUpdates = new ReentrantLock();
    public static ReentrantLock GT_Cable_transferElectricity = new ReentrantLock();
    public static ReentrantLock GT_Utility_moveStackIntoPipe = new ReentrantLock();
    public static ReentrantLock GT_MultiBlockBase_dumpFluid = new ReentrantLock();
    public static ReentrantLock GT_MultiBlockBase_updateSlots = new ReentrantLock();
    public static ReentrantLock GT_InputBus_fillStacksIntoFirstSlots = new ReentrantLock();
    public static ReentrantLock GT_BaseMetaTileEntity_generatePowerNodes = new ReentrantLock();
    private static ExecutorService TEupdatepool;
    private static List<ReentrantLock> locks;

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
        locks = Arrays.asList(AE2_NetworkMonitor_postChange, AE2_NetworkMonitor_injectItems, AE2_NetworkMonitor_extractItems, AE2_GridStorageCache_cellUpdate, PR_WirePropagator_propagateTo, MC_World_notifyBlockOfNeighborChange, MC_WorldServer_tickUpdates, GT_Cable_transferElectricity, GT_Utility_moveStackIntoPipe, GT_MultiBlockBase_dumpFluid, GT_MultiBlockBase_updateSlots, GT_InputBus_fillStacksIntoFirstSlots, GT_BaseMetaTileEntity_generatePowerNodes);
    }

    public static void TEsubmit(List loadedTileEntityList) throws InterruptedException, ExecutionException {
        Iterator iterator = loadedTileEntityList.iterator();
        List<TileEntity> other = new ArrayList<>();
        List<Future> futures = new ArrayList<>();

        while (iterator.hasNext()) {
            TileEntity tileentity = (TileEntity) iterator.next();
            if (tileentity instanceof gregtech.api.metatileentity.BaseMetaTileEntity || tileentity instanceof gregtech.api.metatileentity.BaseMetaPipeEntity) {
                futures.add(TEupdatepool.submit(() -> {
                    TEupdate(tileentity);
                }));
            } else {
                other.add(tileentity);
            }
        }

        TEupdateSingle(other);

        for (Future f : futures) {
            f.get();
        }
    }

    private static void TEupdateSingle(List loadedTileEntityList) {
        for (Object o : loadedTileEntityList) {
            TileEntity tileentity = (TileEntity) o;
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
            } finally {
                unlocks();
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

    public static void unlocks() {
        for (ReentrantLock lock : locks) {
            if (lock.isHeldByCurrentThread()) {
                MyGTNH.info(String.format("Unlock %s", lock));
                lock.unlock();
            }
        }
    }
}
