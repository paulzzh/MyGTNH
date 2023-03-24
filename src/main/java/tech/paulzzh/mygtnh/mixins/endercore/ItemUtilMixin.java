package tech.paulzzh.mygtnh.mixins.endercore;

import com.enderio.core.common.util.ItemUtil;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import tech.paulzzh.mygtnh.mixinhelpers.endercore.ISlotIterator;
import tech.paulzzh.mygtnh.mixinhelpers.endercore.invSlotter;
import tech.paulzzh.mygtnh.mixinhelpers.endercore.sidedSlotter;

import static com.enderio.core.common.util.ItemUtil.areStackMergable;

//Copied from EnderCore https://github.com/GTNewHorizons/EnderCore

@Mixin(value = ItemUtil.class, remap = false, priority = 1)
public abstract class ItemUtilMixin {
    /**
     * @author Paulzzh
     * @reason WTF??
     */
    @Overwrite
    public static int doInsertItem(IInventory inv, int startSlot, int endSlot, ItemStack item) {
        return doInsertItemInv(
                inv,
                null,
                new invSlotter(startSlot, endSlot),
                item,
                ForgeDirection.UNKNOWN,
                true);
    }

    /**
     * @author Paulzzh
     * @reason WTF??
     */
    @Overwrite
    public static int doInsertItem(IInventory inv, int startSlot, int endSlot, ItemStack item, boolean doInsert) {
        return doInsertItemInv(
                inv,
                null,
                new invSlotter(startSlot, endSlot),
                item,
                ForgeDirection.UNKNOWN,
                doInsert);
    }

    /**
     * @author Paulzzh
     * @reason WTF??
     */
    @Overwrite
    private static int doInsertItemInv(IInventory inv, ItemStack item, ForgeDirection inventorySide, boolean doInsert) {
        final ISidedInventory sidedInv = inv instanceof ISidedInventory ? (ISidedInventory) inv : null;
        ISlotIterator slots;
        if (sidedInv != null) {
            if (inventorySide == null) {
                inventorySide = ForgeDirection.UNKNOWN;
            }
            // Note: This is thread-safe now.
            slots = new sidedSlotter(sidedInv.getAccessibleSlotsFromSide(inventorySide.ordinal()));
        } else {
            slots = new invSlotter(0, inv.getSizeInventory());
        }
        return doInsertItemInv(inv, sidedInv, slots, item, inventorySide, doInsert);
    }

    private final static int min(int i1, int i2, int i3) {
        return i1 < i2 ? (i1 < i3 ? i1 : i3) : (i2 < i3 ? i2 : i3);
    }

    private static int doInsertItemInv(IInventory inv, ISidedInventory sidedInv, ISlotIterator slots, ItemStack item,
                                       ForgeDirection inventorySide, boolean doInsert) {
        int numInserted = 0;
        int numToInsert = item.stackSize;
        int firstFreeSlot = -1;

        // PASS1: Try to add to an existing stack
        while (numToInsert > 0 && slots.hasNext()) {
            final int slot = slots.nextSlot();
            if (sidedInv == null || sidedInv.canInsertItem(slot, item, inventorySide.ordinal())) {
                final ItemStack contents = inv.getStackInSlot(slot);
                if (contents != null) {
                    if (areStackMergable(contents, item)) {
                        final int freeSpace = Math.min(inv.getInventoryStackLimit(), contents.getMaxStackSize())
                                - contents.stackSize; // some inventories like using itemstacks with invalid stack sizes
                        if (freeSpace > 0) {
                            final int noToInsert = Math.min(numToInsert, freeSpace);
                            final ItemStack toInsert = item.copy();
                            toInsert.stackSize = contents.stackSize + noToInsert;
                            // isItemValidForSlot() may check the stacksize, so give it the number the stack would have
                            // in the end.
                            // If it does something funny, like "only even numbers", we are screwed.
                            if (sidedInv != null || inv.isItemValidForSlot(slot, toInsert)) {
                                numInserted += noToInsert;
                                numToInsert -= noToInsert;
                                if (doInsert) {
                                    inv.setInventorySlotContents(slot, toInsert);
                                }
                            }
                        }
                    }
                } else if (firstFreeSlot == -1) {
                    firstFreeSlot = slot;
                }
            }
        }

        // PASS2: Try to insert into an empty slot
        if (numToInsert > 0 && firstFreeSlot != -1) {
            final ItemStack toInsert = item.copy();
            toInsert.stackSize = min(numToInsert, inv.getInventoryStackLimit(), toInsert.getMaxStackSize()); // some
            // inventories
            // like
            // using
            // itemstacks
            // with
            // invalid
            // stack
            // sizes
            if (sidedInv != null || inv.isItemValidForSlot(firstFreeSlot, toInsert)) {
                numInserted += toInsert.stackSize;
                numToInsert -= toInsert.stackSize;
                if (doInsert) {
                    inv.setInventorySlotContents(firstFreeSlot, toInsert);
                }
            }
        }

        if (numInserted > 0 && doInsert) {
            inv.markDirty();
        }
        return numInserted;
    }

}