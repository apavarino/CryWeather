package me.crylonz;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabCompletion implements TabCompleter {

    private List<String> list = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        list.clear();

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("cw")) {

                if (args.length == 1) {
                    list.add("raincontrol");
                    list.add("day");
                    list.add("night");
                    list.add("morning");
                    list.add("afternoon");
                    list.add("evening");
                    list.add("sun");
                    list.add("rain");
                    list.add("set");
                    list.add("worldspeed");
                }

                if (args.length == 2) {

                    if (args[0].equalsIgnoreCase("raincontrol")) {
                        list.add("on");
                        list.add("off");
                    } else if (args[0].equalsIgnoreCase("worldspeed")) {
                        list.add("-10");
                        list.add("-9");
                        list.add("-8");
                        list.add("-7");
                        list.add("-6");
                        list.add("-5");
                        list.add("-4");
                        list.add("-3");
                        list.add("-2");
                        list.add("-1");
                        list.add("1");
                        list.add("2");
                        list.add("3");
                        list.add("4");
                        list.add("5");
                        list.add("6");
                        list.add("7");
                        list.add("8");
                        list.add("9");
                        list.add("10");
                    } else {
                        for(World world :Bukkit.getWorlds()) {
                            list.add(world.getName());
                        }
                    }
                }
            }
        }
        return list;
    }
}