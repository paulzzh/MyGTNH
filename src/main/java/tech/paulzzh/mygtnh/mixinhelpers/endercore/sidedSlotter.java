package tech.paulzzh.mygtnh.mixinhelpers.endercore;

public class sidedSlotter implements ISlotIterator {
    private int[] slots;
    private int current;

    public sidedSlotter(int[] slots) {
        this.slots = slots;
        this.current = 0;
    }

    @Override
    public final int nextSlot() {
        return slots[current++];
    }

    @Override
    public final boolean hasNext() {
        return slots != null && current < slots.length;
    }
}
