package com.paulzzh.mygtnh.events;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import static com.paulzzh.mygtnh.MyGTNH.autoSave;
import static com.paulzzh.mygtnh.MyGTNH.tickTime;
import static com.paulzzh.mygtnh.Utils.notifyMaintenance;
import static com.paulzzh.mygtnh.Utils.setFinalStatic;

public class CommandMyGTNH extends CommandBase {
    @Override
    public String getCommandName() {
        return "mygtnh";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/mygtnh <action> <args...>";
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "dump", "maintenance", "save", "tps");
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("dump")) {
                return getListOfStringsMatchingLastWord(args, "infusion");
            }
            if (args[0].equalsIgnoreCase("tps")) {
                return getListOfStringsMatchingLastWord(args, "20.0");
            }
        }
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.addChatMessage(new ChatComponentTranslation("§cInvalid arguments"));
            sender.addChatMessage(new ChatComponentTranslation("/mygtnh help"));
        } else {
            if (args[0].equalsIgnoreCase("help")) {
                sender.addChatMessage(new ChatComponentTranslation("/mygtnh dump infusion"));
                sender.addChatMessage(new ChatComponentTranslation("/mygtnh maintenance"));
                sender.addChatMessage(new ChatComponentTranslation("/mygtnh save"));
                sender.addChatMessage(new ChatComponentTranslation("/mygtnh tps 20.0"));
            } else if (args[0].equalsIgnoreCase("maintenance")) {
                notifyMaintenance(sender);
            } else if (args[0].equalsIgnoreCase("save")) {
                if (autoSave) {
                    autoSave = false;
                    sender.addChatMessage(new ChatComponentTranslation("AutoSave: off"));
                } else {
                    autoSave = true;
                    sender.addChatMessage(new ChatComponentTranslation("AutoSave: on"));
                }
            } else if (args[0].equalsIgnoreCase("tps")) {
                if (args.length >= 2) {
                    tickTime = (int) (1000000000 / parseDouble(sender, args[1]));
                    try {
                        Field f = MinecraftServer.class.getDeclaredField("TICK_TIME");
                        setFinalStatic(f, tickTime);
                    } catch (Exception ignore) {
                    }
                }
                if (tickTime != 0L) {
                    sender.addChatMessage(new ChatComponentTranslation(String.format("Current target tps: %.2f (%dns)", 1000000000D / tickTime, tickTime)));
                }
            } else if (args.length >= 2) {
                if (args[0].equalsIgnoreCase("dump") && args[1].equalsIgnoreCase("infusion")) {
                    sender.addChatMessage(new ChatComponentTranslation("dumping..."));
                    try {
                        FileWriter writer = new FileWriter("infusion.csv");
                        writer.append("Input,Damage,Output,Damage,Aspects,...\n");
                        for (Object o : ThaumcraftApi.getCraftingRecipes()) {
                            if (o instanceof InfusionRecipe tcRecipe) {
                                if (tcRecipe.getRecipeInput() != null) {
                                    ItemStack input = tcRecipe.getRecipeInput();
                                    ItemStack output = input.copy();
                                    if (tcRecipe.getRecipeOutput() instanceof ItemStack) {
                                        output = (ItemStack) tcRecipe.getRecipeOutput();
                                    }
                                    if (input.getItem() != null && output.getItem() != null) {
                                        AspectList aspects = tcRecipe.getAspects();
                                        writer.append(Item.itemRegistry.getNameForObject(input.getItem()));
                                        writer.append(",");
                                        writer.append(String.valueOf(input.getItemDamage()));
                                        writer.append(",");
                                        writer.append(Item.itemRegistry.getNameForObject(output.getItem()));
                                        writer.append(",");
                                        writer.append(String.valueOf(output.getItemDamage()));
                                        writer.append(",");
                                        writer.append(String.valueOf(aspects.size()));
                                        writer.append(",");
                                        for (Aspect aspect : aspects.getAspects()) {
                                            if (aspect != null) {
                                                writer.append(aspect.getTag());
                                                writer.append(",");
                                                writer.append(String.valueOf(aspects.getAmount(aspect)));
                                                writer.append(",");
                                            }
                                        }
                                        writer.append("\n");
                                        writer.flush();
                                    }
                                }
                            }
                        }
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
