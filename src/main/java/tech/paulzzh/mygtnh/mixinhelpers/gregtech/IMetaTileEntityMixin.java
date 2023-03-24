package tech.paulzzh.mygtnh.mixinhelpers.gregtech;

import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import tech.paulzzh.mygtnh.mcmt.MyLock;

public interface IMetaTileEntityMixin extends IMetaTileEntity {
    MyLock getTankLock();

    MyLock getInvLock();
}
