package com.paulzzh.mygtnh.events;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;

import java.io.FileWriter;
import java.io.IOException;

import static com.paulzzh.mygtnh.Utils.notifyMaintenance;

public class CommandMyGTNH extends CommandBase {
    @Override
    public String getCommandName() {
        return "mygtnh";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/thaumcraft <action> <args...>";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.addChatMessage(new ChatComponentTranslation("§cInvalid arguments"));
            sender.addChatMessage(new ChatComponentTranslation("/mygtnh help"));
        } else {
            if (args[0].equalsIgnoreCase("help")) {
                sender.addChatMessage(new ChatComponentTranslation("/mygtnh dump infusion"));
            } else if (args[0].equalsIgnoreCase("maintenance")) {
                notifyMaintenance(sender);
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
