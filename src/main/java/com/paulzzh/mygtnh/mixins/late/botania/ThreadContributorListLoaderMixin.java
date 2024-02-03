package com.paulzzh.mygtnh.mixins.late.botania;

import com.paulzzh.mygtnh.MyGTNH;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import vazkii.botania.client.core.handler.ContributorFancinessHandler.ThreadContributorListLoader;

@Mixin(value = ThreadContributorListLoader.class, remap = false)
public class ThreadContributorListLoaderMixin {
    /**
     * @author Paulzzh
     * @reason remove Contributor
     */
    @Overwrite
    public void run() {
        MyGTNH.LOG.info("block Botania web request");
    }
}
