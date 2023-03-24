package tech.paulzzh.mygtnh.mixinhelpers.endercore;

public class invSlotter implements ISlotIterator {
    private int end;
    private int current;

    public invSlotter(int start, int end) {
        this.end = end;
        this.current = start;
    }

    @Override
    public final int nextSlot() {
        return current++;
    }

    @Override
    public final boolean hasNext() {
        return current < end;
    }
}
