
package com.rivalrebels.common.command;

import com.rivalrebels.client.render.ModelDebugTransform;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RRModelMoveDebug extends CommandBase {

    @Override
    public String getName() {
        return "rrmodeldebug";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/rrmodeldebug <translate|rotate|add|reset|status|print>";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {

        if (args.length == 1) {

            String command = args[0].toLowerCase();

            if (command.equals("reset")) {
                ModelDebugTransform.reset();
                sender.sendMessage(new TextComponentString("§aTransforms reset!"));
                return;
            }

            if (command.equals("status")) {
                printStatus(sender);
                return;
            }

            if (command.equals("print")) {
                printCode(sender);
                return;
            }
        }

        if (args.length != 3) {
            sender.sendMessage(new TextComponentString("§cUsage: " + getUsage(sender)));
            return;
        }

        String operation = args[0].toLowerCase();
        String axis = args[1].toLowerCase();

        float value;

        try {
            value = Float.parseFloat(args[2]);
        } catch (NumberFormatException e) {
            sender.sendMessage(new TextComponentString("§cInvalid number!"));
            return;
        }

        boolean translate = operation.equals("translate");
        boolean rotate = operation.equals("rotate");
        boolean add = operation.equals("add");

        if (!translate && !rotate && !add) {
            sender.sendMessage(new TextComponentString("§cOperation must be translate, rotate, or add!"));
            return;
        }

        if (!axis.equals("x") && !axis.equals("y") && !axis.equals("z")) {
            sender.sendMessage(new TextComponentString("§cAxis must be x, y, or z!"));
            return;
        }

        if (translate || add) {
            switch (axis) {
                case "x":
                    ModelDebugTransform.translateX =
                        add ? ModelDebugTransform.translateX + value : value;
                    break;

                case "y":
                    ModelDebugTransform.translateY =
                        add ? ModelDebugTransform.translateY + value : value;
                    break;

                case "z":
                    ModelDebugTransform.translateZ =
                        add ? ModelDebugTransform.translateZ + value : value;
                    break;
            }
        } else {
            switch (axis) {
                case "x":
                    ModelDebugTransform.rotateX =
                        add ? ModelDebugTransform.rotateX + value : value;
                    break;

                case "y":
                    ModelDebugTransform.rotateY =
                        add ? ModelDebugTransform.rotateY + value : value;
                    break;

                case "z":
                    ModelDebugTransform.rotateZ =
                        add ? ModelDebugTransform.rotateZ + value : value;
                    break;
            }
        }

        sender.sendMessage(new TextComponentString(
            "§a" + operation + " " + axis + " = " + getValue(translate || add, axis)
        ));
    }

    private float getValue(boolean translation, String axis) {
        if (translation) {
            switch (axis) {
                case "x": return ModelDebugTransform.translateX;
                case "y": return ModelDebugTransform.translateY;
                case "z": return ModelDebugTransform.translateZ;
            }
        } else {
            switch (axis) {
                case "x": return ModelDebugTransform.rotateX;
                case "y": return ModelDebugTransform.rotateY;
                case "z": return ModelDebugTransform.rotateZ;
            }
        }

        return 0;
    }

    private void printStatus(ICommandSender sender) {
        sender.sendMessage(new TextComponentString("§e--- Model Transform ---"));

        sender.sendMessage(new TextComponentString(
            "§bTranslation: §f"
            + ModelDebugTransform.translateX + ", "
            + ModelDebugTransform.translateY + ", "
            + ModelDebugTransform.translateZ
        ));

        sender.sendMessage(new TextComponentString(
            "§bRotation: §f"
            + ModelDebugTransform.rotateX + ", "
            + ModelDebugTransform.rotateY + ", "
            + ModelDebugTransform.rotateZ
        ));
    }

    private void printCode(ICommandSender sender) {
        sender.sendMessage(new TextComponentString("§a--- Copy these into your renderer ---"));

        sender.sendMessage(new TextComponentString(
            "§fGL11.glTranslatef("
            + ModelDebugTransform.translateX + "f, "
            + ModelDebugTransform.translateY + "f, "
            + ModelDebugTransform.translateZ + "f);"
        ));

        sender.sendMessage(new TextComponentString(
            "§fGL11.glRotatef("
            + ModelDebugTransform.rotateX + "f, 1, 0, 0);"
        ));

        sender.sendMessage(new TextComponentString(
            "§fGL11.glRotatef("
            + ModelDebugTransform.rotateY + "f, 0, 1, 0);"
        ));

        sender.sendMessage(new TextComponentString(
            "§fGL11.glRotatef("
            + ModelDebugTransform.rotateZ + "f, 0, 0, 1);"
        ));
    }

    @Override
    public List<String> getTabCompletions(
        MinecraftServer server,
        ICommandSender sender,
        String[] args,
        BlockPos pos
    ) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(
                args,
                "translate", "rotate", "add", "reset", "status", "print"
            );
        }

        if (args.length == 2 &&
            (args[0].equalsIgnoreCase("translate") ||
             args[0].equalsIgnoreCase("rotate") ||
             args[0].equalsIgnoreCase("add"))) {

            return getListOfStringsMatchingLastWord(args, "x", "y", "z");
        }

        if (args.length == 3 &&
            (args[0].equalsIgnoreCase("translate") ||
             args[0].equalsIgnoreCase("rotate") ||
             args[0].equalsIgnoreCase("add"))) {

            return getListOfStringsMatchingLastWord(
                args, "0", "0.01", "0.1", "0.5", "1", "5", "15", "45", "90"
            );
        }

        return Collections.emptyList();
    }
}
