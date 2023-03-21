package tech.paulzzh.mygtnh.mcmt;

import tech.paulzzh.mygtnh.MyGTNH;

import java.util.concurrent.locks.ReentrantLock;

public class MyLock extends ReentrantLock {
    public void lock() {
        super.lock();
        FJPool.lockmap.get(Thread.currentThread().getId()).add(this);
        //MyGTNH.info(Utils.getMethodFullName(3));
    }

    public void unlock() {
        FJPool.lockmap.get(Thread.currentThread().getId()).remove(this);
        super.unlock();
    }

    public boolean tryLock() {
        if (super.tryLock()) {
            FJPool.lockmap.get(Thread.currentThread().getId()).add(this);
            return true;
        } else {
            return false;
        }
    }

    public void unlockall() {
        //while(isHeldByCurrentThread()) {
        //    unlock();
        //}
        FJPool.unlock();
        MyGTNH.info(this.toString());
    }
}
