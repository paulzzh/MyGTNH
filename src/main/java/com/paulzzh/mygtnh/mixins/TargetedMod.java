package com.paulzzh.mygtnh.mixins;

import cpw.mods.fml.common.Mod;

public enum TargetedMod {
    VANILLA("Minecraft", null),
    GALACTICRAFT("GalacticraftCore", null, "GalacticraftCore"),
    BIOMESOPLENTY("BiomesOPlenty", null, "BiomesOPlenty"),
    GALAXYSPACE("GalaxySpace", null, "GalaxySpace"),
    GREGTECH("gregtech", null, "gregtech"),
    DRACONIC("Draconic Evolution", null, "DraconicEvolution"),
    BOTANIA("Botania", null, "Botania"),
    AROMA1997CORE("Aroma1997Core", null, "Aroma1997Core"),
    JOURNEYMAP("JourneyMap", null, "journeymap"),
    THAUMCRAFT("Thaumcraft", null, "Thaumcraft"),
    WARPTHEORY("WarpTheory", null, "WarpTheory"),
    NUTRITION("Nutrition", null, "nutrition"),
    SERVERUTILITIES("Server Utilities", null, "serverutilities"),
    WORLDEDIT("worldedit", null, "worldedit"),
    ADVENTUREBACKPACK("Adventure Backpack", null, "adventurebackpack"),
    GTNHINTERGALACTIC("GTNH-Intergalactic", null, "gtnhintergalactic"),
    IC2("IC2", "ic2.core.coremod.IC2core", "IC2"),

    ;
    /**
     * The "name" in the {@link Mod @Mod} annotation
     */
    public final String modName;
    /**
     * Class that implements the IFMLLoadingPlugin interface
     */
    public final String coreModClass;
    /**
     * The "modid" in the {@link Mod @Mod} annotation
     */
    public final String modId;

    TargetedMod(String modName, String coreModClass) {
        this(modName, coreModClass, null);
    }

    TargetedMod(String modName, String coreModClass, String modId) {
        this.modName = modName;
        this.coreModClass = coreModClass;
        this.modId = modId;
    }

    @Override
    public String toString() {
        return "TargetedMod{modName='" + modName + "', coreModClass='" + coreModClass + "', modId='" + modId + "'}";
    }
}
