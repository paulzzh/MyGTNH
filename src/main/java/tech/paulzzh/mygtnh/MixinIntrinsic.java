package tech.paulzzh.mygtnh;

import appeng.api.config.Actionable;
import appeng.api.networking.IGridHost;
import appeng.api.networking.IGridNode;
import appeng.api.networking.security.BaseActionSource;
import appeng.api.storage.ICellProvider;
import appeng.api.storage.IMEInventoryHandler;
import appeng.api.storage.data.IAEFluidStack;
import appeng.api.storage.data.IAEItemStack;
import appeng.api.storage.data.IAEStack;
import gregtech.api.graphs.consumers.ConsumerNode;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Output;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public interface MixinIntrinsic<T extends IAEStack<T>> {
    void removeNode(final IGridNode node, final IGridHost machine);

    void addNode(final IGridNode node, final IGridHost machine);

    void registerCellProvider(final ICellProvider provider);

    void unregisterCellProvider(final ICellProvider provider);

    IMEInventoryHandler<IAEItemStack> getItemInventoryHandler();

    IMEInventoryHandler<IAEFluidStack> getFluidInventoryHandler();

    void postChange(final boolean add, final Iterable<T> changes, final BaseActionSource src);

    T injectItems(final T input, final Actionable mode, final BaseActionSource src);

    T extractItems(final T input, final Actionable mode, final BaseActionSource src);

    void generatePowerNodes();

    long transferElectricity(byte aSide, long aVoltage, long aAmperage, HashSet<TileEntity> aAlreadyPassedSet);

    void fillStacksIntoFirstSlots();

    void updateNeighbours(int mStrongRedstone, int oStrongRedstone);

    boolean dumpFluid(List<GT_MetaTileEntity_Hatch_Output> aOutputHatches, FluidStack copiedFluidStack, boolean restrictiveHatchesOnly);

    void updateSlots();

    byte moveStackIntoPipe(IInventory aTileEntity1, Object aTileEntity2, int[] aGrabSlots, int aGrabFrom,
                           int aPutTo, List<ItemStack> aFilter, boolean aInvertFilter, byte aMaxTargetStackSize,
                           byte aMinTargetStackSize, byte aMaxMoveAtOnce, byte aMinMoveAtOnce, boolean dropItem);

    void notifyBlockOfNeighborChange(int p_147460_1_, int p_147460_2_, int p_147460_3_, final Block p_147460_4_);

    int doInsertItem(Object into, ItemStack item, ForgeDirection side);

    int fill(FluidStack aFluid, boolean doFill);

    long getAverageElectricInput();

    long injectEnergyUnits(byte aSide, long aVoltage, long aAmperage);

    boolean addConsumer(TileEntity aTileEntity, byte aSide, int aNodeValue,
                        ArrayList<ConsumerNode> aConsumers);

    String[] getInfoData();

    void updateEntity();

}
