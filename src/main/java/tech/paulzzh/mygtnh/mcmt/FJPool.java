package tech.paulzzh.mygtnh.mcmt;

import gregtech.api.metatileentity.BaseMetaTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import tech.paulzzh.mygtnh.MyGTNH;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FJPool {
    private static final List<Runnable> NBCtasks = Collections.synchronizedList(new ArrayList<>());
    public static MyLock AE2_NetworkMonitor_postChange = new MyLock();
    public static MyLock AE2_NetworkMonitor_Items = new MyLock();
    public static MyLock AE2_GridStorageCache = new MyLock();
    public static MyLock PR_WirePropagator_propagateTo = new MyLock();
    public static MyLock MC_WorldServer_pendingTickListEntriesTreeSet = new MyLock();
    public static MyLock GT_IEnergyConnected_emitEnergyToNetwork = new MyLock();
    public static MyLock EC_ItemUtil_doInsertItem = new MyLock();
    public static HashMap<Long, MyLock> tickmap;
    protected static HashMap<Long, List<MyLock>> lockmap;
    private static ExecutorService TEupdatepool;
    private static ExecutorService NBchangepool;
    private static List<MyLock> locks;
    private static CountDownLatch latch;
    ;

    public static void setupThreadPool(int count) {
        AtomicInteger id = new AtomicInteger();
        final ClassLoader cl = MyGTNH.class.getClassLoader();

        ForkJoinPool.ForkJoinWorkerThreadFactory factory = p -> {
            ForkJoinWorkerThread fjwt = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(p);
            fjwt.setName("MCMT-Tick-Pool-Thread-" + id.getAndIncrement());
            fjwt.setContextClassLoader(cl);
            return fjwt;
        };

        Thread.UncaughtExceptionHandler handler = (t, throwable) -> {
            long tid = Thread.currentThread().getId();
            MyGTNH.info("exception UncaughtExceptionHandler");
            MyGTNH.info(lockmap.get(tid).toString());
            throwable.printStackTrace();
        };

        TEupdatepool = new ForkJoinPool(count, factory, handler, false);

        locks = Arrays.asList(EC_ItemUtil_doInsertItem, GT_IEnergyConnected_emitEnergyToNetwork, MC_WorldServer_pendingTickListEntriesTreeSet,
                AE2_GridStorageCache, AE2_NetworkMonitor_Items, AE2_NetworkMonitor_postChange);
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
        List<TileEntity> gt = new ArrayList<>();
        List<TileEntity> other = new ArrayList<>();

        while (iterator.hasNext()) {
            TileEntity tileentity = (TileEntity) iterator.next();
            if (tileentity instanceof gregtech.api.metatileentity.BaseMetaTileEntity) {
                BaseMetaTileEntity mMetaTileEntity = (BaseMetaTileEntity) tileentity;
                if (mMetaTileEntity.isEnetOutput()) {
                    other.add(tileentity);
                } else {
                    gt.add(tileentity);
                }
            } else {
                other.add(tileentity);
            }
        }

        latch = new CountDownLatch(gt.size());
        for (TileEntity tileentity : gt) {
            TEupdatepool.execute(() -> {
                TEupdate(world, tileentity);
            });
        }

        TEupdateSingle(world, other);
        latch.await();

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
        if (tileentity == null) {
            //MyGTNH.info("null TileEntity");
            latch.countDown();
            return;
        }
        long tid = Thread.currentThread().getId();
        lockmap.put(tid, new ArrayList<>());
        try {

            if (!tileentity.isInvalid() && tileentity.hasWorldObj() && world.blockExists(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord)) {
                tileentity.updateEntity();
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
        } catch (Throwable throwable) {
            MyGTNH.info(lockmap.get(tid).toString());
            throwable.printStackTrace();
        } finally {
            unlocks();
            latch.countDown();
        }
    }

    public static List<MyLock> unlockte() {
        long tid = Thread.currentThread().getId();
        if (lockmap.containsKey(tid)) {
            List<MyLock> backup = lockmap.get(tid);
            //if (backup.size() > 0) {
            //    MyGTNH.info(String.valueOf(backup.size()));
            //}
            for (MyLock l : backup) {
                l.unlockfast();
            }
            lockmap.put(tid, new ArrayList<>());
            return backup;
        }
        return new ArrayList<>();
    }

    public static void lockte() {
        long tid = Thread.currentThread().getId();
        if (tickmap.containsKey(tid)) {
            tickmap.get(tid).lock();
        }
    }

    public static List<MyLock> unlocks() {
        List<MyLock> backup = unlockte();
        for (MyLock lock : locks) {
            while (lock.isHeldByCurrentThread()) {
                //MyGTNH.info(String.format("Unlock %s", lock));
                backup.add(lock);
                lock.unlock();
            }
        }
        return backup;
    }
}
