package com.rivalrebels.common.command;

import com.rivalrebels.common.init.RRConfigOptions;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RRConfig extends CommandBase {
    @Override
    public String getName() {
        return "rrconfig";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/" + getName();
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        if(args.length == 2){
            String str = args[0];
            if(str.equals("enable_old_nuke")){
                String str2 = args[1];
                boolean i = Boolean.parseBoolean(str2);
                String str3 = "§e|if you are getting this message, try typing the command again. If that doesn't work, open an issue at https://github.com/a-weeb-programmer/rival_rebels|".substring(1) + "§r".substring(1);
                if(i){
                    str3 = "will";
                } else {
                    str3 = "will not";
                }
                RRConfigOptions.render_old_nuke = i;
                sender.sendMessage(new TextComponentString("§cThe game ".substring(1) + str3 + " render the old nuke model"));
                return;
            }
        }
        sender.sendMessage(new TextComponentString("§cUsage: /rrconfig enable_old_nuke | more commands coming soon".substring(1)));
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 3;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        List<String> list = new ArrayList<>();
        list.add("render_old_nuke");
        return list;
    }
}
