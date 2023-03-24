package tech.paulzzh.mygtnh.mcmt;

import tech.paulzzh.mygtnh.MyGTNH;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock extends ReentrantLock {
    public void unlock() {
        long tid = Thread.currentThread().getId();
        if (FJPool.lockmap.containsKey(tid)) {
            FJPool.lockmap.get(tid).remove(this);
        }
        super.unlock();
    }

    public void unlockfast() {
        super.unlock();
    }

    private boolean _tryLock() {
        try {
            return super.tryLock(100, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            return false;
        }
    }

    public void trylock() {
        if (!MyGTNH.serverStarting) {
            return;
        }
        if (_tryLock()) {
            FJPool.lockmap.get(Thread.currentThread().getId()).add(this);
        } else {
            List<MyLock> backup = FJPool.unlocks();
            lock();
            FJPool.lockmap.get(Thread.currentThread().getId()).add(this);
            for (MyLock l : backup) {
                l.trylock();
            }
        }
    }
}
