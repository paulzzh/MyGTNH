package tech.paulzzh.mygtnh.mcmt;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import tech.paulzzh.mygtnh.MyGTNH;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class FJPool {
    private static final List<Runnable> NBCtasks = Collections.synchronizedList(new ArrayList<>());
    public static ReentrantLock AE2_NetworkMonitor_postChange = new ReentrantLock();
    public static ReentrantLock AE2_NetworkMonitor_injectItems = new ReentrantLock();
    public static ReentrantLock AE2_NetworkMonitor_extractItems = new ReentrantLock();
    public static ReentrantLock AE2_GridStorageCache = new ReentrantLock();
    public static ReentrantLock PR_WirePropagator_propagateTo = new ReentrantLock();
    public static ReentrantLock MC_WorldServer_pendingTickListEntriesTreeSet = new ReentrantLock();
    public static ReentrantLock GT_IEnergyConnected_emitEnergyToNetwork = new ReentrantLock();
    public static ReentrantLock EC_ItemUtil_doInsertItem = new ReentrantLock();
    public static HashMap<Long, MyLock> tickmap;
    protected static HashMap<Long, List<MyLock>> lockmap;
    private static ExecutorService TEupdatepool;
    private static ExecutorService NBchangepool;
    private static List<ReentrantLock> locks;
    ;

    public static void setupThreadPool(int count) {
        AtomicInteger tid = new AtomicInteger();
        final ClassLoader cl = MyGTNH.class.getClassLoader();

        ForkJoinPool.ForkJoinWorkerThreadFactory factory = p -> {
            ForkJoinWorkerThread fjwt = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(p);
            fjwt.setName("MCMT-Tick-Pool-Thread-" + tid.getAndIncrement());
            fjwt.setContextClassLoader(cl);
            return fjwt;
        };

        ForkJoinPool.ForkJoinWorkerThreadFactory factory2 = p -> {
            ForkJoinWorkerThread fjwt = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(p);
            fjwt.setName("MCMT-NeighborBlock-Pool-Thread");
            fjwt.setContextClassLoader(cl);
            return fjwt;
        };

        TEupdatepool = new ForkJoinPool(count, factory, null, false);
        //NBchangepool = new ForkJoinPool(1, factory2, null, false);

        locks = Arrays.asList(EC_ItemUtil_doInsertItem, GT_IEnergyConnected_emitEnergyToNetwork, MC_WorldServer_pendingTickListEntriesTreeSet,
                AE2_GridStorageCache, AE2_NetworkMonitor_injectItems, AE2_NetworkMonitor_extractItems, AE2_NetworkMonitor_postChange);
        lockmap = new HashMap<>();
        tickmap = new HashMap<>();
    }

    public static void NBsubmit(Runnable run) {
        if (run != null) {
            NBCtasks.add(run);
        } else {
            MyGTNH.info("why null???");
        }
    }

    public static void TEsubmit(World world, List loadedTileEntityList) throws InterruptedException, ExecutionException {
        if (loadedTileEntityList.size() > 0) {
            unlocks();
        }

        Iterator iterator = loadedTileEntityList.iterator();
        List<TileEntity> other = new ArrayList<>();
        List<Future> futures = new ArrayList<>();

        while (iterator.hasNext()) {
            TileEntity tileentity = (TileEntity) iterator.next();
            if (tileentity instanceof gregtech.api.metatileentity.BaseMetaTileEntity || tileentity instanceof gregtech.api.metatileentity.BaseMetaPipeEntity) {
                futures.add(TEupdatepool.submit(() -> {
                    TEupdate(world, tileentity);
                }));
            } else {
                other.add(tileentity);
            }
        }

        TEupdateSingle(world, other);

        for (Future f : futures) {
            f.get();
        }

        //MyGTNH.info("Neighbor");

        while (!NBCtasks.isEmpty()) {
            List<Runnable> backup = new ArrayList<>(NBCtasks);
            NBCtasks.clear();
            for (Runnable r : backup) {
                r.run();
            }
        }
    }

    private static void TEupdateSingle(World world, List loadedTileEntityList) {
        Iterator iterator = loadedTileEntityList.iterator();
        while (iterator.hasNext()) {
            TileEntity tileentity = (TileEntity) iterator.next();
            TEupdate(world, tileentity);
        }
    }

    private static void TEupdate(World world, TileEntity tileentity) {
        long tid = Thread.currentThread().getId();
        tickmap.remove(tid);
        if (!lockmap.containsKey(tid)) {
            lockmap.put(tid, new ArrayList<>());
        } else if (lockmap.get(tid).size() != 0) {
            MyGTNH.info(lockmap.toString());
        }

        if (tileentity == null) {
            //MyGTNH.info("null TileEntity");
            return;
        }

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

    private static void NBchange(Block block, World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
        long tid = Thread.currentThread().getId();
        if (!lockmap.containsKey(tid)) {
            lockmap.put(tid, new ArrayList<>());
        } else if (lockmap.get(tid).size() != 0) {
            MyGTNH.info(lockmap.toString());
        }
        try {
            block.onNeighborBlockChange(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_5_);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            unlocks();
        }
    }

    public static void unlock() {
        long tid = Thread.currentThread().getId();
        if (lockmap.containsKey(tid)) {
            for (MyLock l : new ArrayList<>(lockmap.get(tid))) {
                l.unlock();
            }
        }
    }

    public static void unlockte() {
        long tid = Thread.currentThread().getId();
        if (tickmap.containsKey(tid)) {
            tickmap.get(tid).unlockall();
        }
    }

    public static void lockte() {
        long tid = Thread.currentThread().getId();
        if (tickmap.containsKey(tid)) {
            tickmap.get(tid).lock();
        }
    }

    public static void unlocks() {
        unlock();
        for (ReentrantLock lock : locks) {
            while (lock.isHeldByCurrentThread()) {
                MyGTNH.info(String.format("Unlock %s", lock));
                lock.unlock();
            }
        }
    }
}
