package me.crylonz;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;
import java.util.logging.Logger;


public class CryWeather extends JavaPlugin implements Listener {
    private final Logger log = Logger.getLogger("Minecraft");
    private int timeSpeed;
    private String playerMsg;

    public void onEnable() {

        Objects.requireNonNull(getCommand("cw")).setTabCompleter(new TabCompletion());

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(this, this);

        // On teste si config.yml existe
        File configFile = new File(this.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveDefaultConfig();
        }


        timeSpeed = getConfig().getInt("worldTimeSpeed");
        if (timeSpeed > 10 || timeSpeed < -10)
            timeSpeed = 1;

        Bukkit.getServer().getScheduler().runTaskTimer(this, new Runnable() {
            public void run() {
                CryWeather.this.syncTime();
            }
        }, 0L, 1L);

    }

    public void onDisable() {
    }

    private void syncTime() {
        for (World world : Bukkit.getWorlds()) {
            world.setFullTime((world.getFullTime() + timeSpeed - 1));
        }
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        if ((e.toWeatherState()) && !getConfig().getBoolean("rainEnabled")) {
            e.setCancelled(true);
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {
        World world;
        Player player;
        if ((sender instanceof Player)) {
            player = (Player) sender;
            world = player.getWorld();

            if (cmd.getName().equalsIgnoreCase("cw")) {

                /// Rain Management
                if (args[0].equalsIgnoreCase("raincontrol") && player.hasPermission("cryweather.rainControl")) {

                    if (args.length == 2) {
                        if (args[1].equalsIgnoreCase("on")) {
                            getConfig().set("rainEnabled", Boolean.TRUE);
                            saveConfig();
                            playerMsg = ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "Rain is now enabled on the server";
                        } else if (args[1].equalsIgnoreCase("off")) {
                            getConfig().set("rainEnabled", Boolean.FALSE);
                            saveConfig();
                            world.setThundering(false);
                            world.setStorm(false);
                            playerMsg = ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "Rain is now disabled on the server";
                        } else {
                            playerMsg = ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Bad parameters : ON|OFF";
                        }
                    } else {
                        playerMsg = ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Bad parameters : ON|OFF";
                    }
                }

                /// DAY
                else if (args[0].equalsIgnoreCase("day") && player.hasPermission("cryweather.day")) {

                    if (args.length == 1) {
                        world.setTime(0L);
                        playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is day !");
                    } else if (args.length == 2) {
                        if (player.hasPermission("cryweather.day.otherworld")) {
                            World otherworld = getServer().getWorld(args[1]);
                            if (otherworld == null) {
                                playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "This world is not available !");
                            } else {
                                otherworld.setTime(0L);
                                playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is day in " + otherworld.getName() + " !");
                            }
                        }

                    } else {
                        playerMsg = ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Too many arguments !";
                    }
                }

                /// NIGHT
                else if (args[0].equalsIgnoreCase("night") && player.hasPermission("cryweather.night")) {
                    if (args.length == 1) {
                        world.setTime(14000L);
                        playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is night !");
                    } else if (args.length == 2) {
                        if (player.hasPermission("cryweather.night.otherworld")) {
                            World otherworld = getServer().getWorld(args[1]);
                            if (otherworld == null) {
                                playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "This world is not available !");
                            } else {
                                otherworld.setTime(14000L);
                                playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is night in " + otherworld.getName() + " !");
                            }
                        }

                    } else {
                        playerMsg = ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Too many arguments !";
                    }
                }

                /// MORNING
                else if (args[0].equalsIgnoreCase("morning") && player.hasPermission("cryweather.morning")) {

                    if (args.length == 1) {
                        world.setTime(23000L);
                        playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is morning !");
                    } else if (args.length == 2) {
                        if (player.hasPermission("cryweather.morning.otherworld")) {
                            World otherworld = getServer().getWorld(args[1]);
                            if (otherworld == null) {
                                playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "This world is not available !");
                            } else {
                                otherworld.setTime(23000L);
                                playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is morning in " + otherworld.getName() + " !");
                            }
                        }

                    } else {
                        playerMsg = ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Too many arguments !";
                    }
                }

                /// AFTERNOON
                else if (args[0].equalsIgnoreCase("afternoon") && player.hasPermission("cryweather.afternoon")) {
                    if (args.length == 1) {
                        world.setTime(6500L);
                        playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is afternoon !");
                    } else if (args.length == 2) {
                        if (player.hasPermission("cryweather.afternoon.otherworld")) {
                            World otherworld = getServer().getWorld(args[1]);
                            if (otherworld == null) {
                                playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "This world is not available !");
                            } else {
                                otherworld.setTime(6500L);
                                playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is afternoon in " + otherworld.getName() + " !");
                            }
                        }

                    } else {
                        playerMsg = ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Too many arguments !";
                    }
                }

                /// EVENING
                else if (args[0].equalsIgnoreCase("evening") && player.hasPermission("cryweather.evening")) {

                    if (args.length == 1) {
                        world.setTime(13000L);
                        playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is evening !");
                    } else if (args.length == 2) {
                        if (player.hasPermission("cryweather.evening.otherworld")) {
                            World otherworld = getServer().getWorld(args[1]);
                            if (otherworld == null) {
                                playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "This world is not available !");
                            } else {
                                otherworld.setTime(13000L);
                                playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is evening in " + otherworld.getName() + " !");
                            }
                        }

                    } else {
                        playerMsg = ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Too many arguments !";
                    }
                }

                /// SUN
                else if (args[0].equalsIgnoreCase("sun") && player.hasPermission("cryweather.sun")) {

                    if (args.length == 1) {
                        world.setThundering(false);
                        world.setStorm(false);
                        playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "The weather is nice !");
                    } else if (args.length == 2 || args.length == 3) {
                        if (player.hasPermission("cryweather.sun.otherworld")) {
                            World otherworld = getServer().getWorld(args[1]);
                            if (otherworld == null) {
                                playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "This world is not available !");
                            } else {
                                try {
                                    otherworld.setThundering(false);
                                    otherworld.setStorm(false);
                                    if (args.length == 3) {
                                        otherworld.setWeatherDuration(Integer.parseInt(args[2]));
                                        playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "The weather is nice in " + otherworld.getName() + " during " + args[2] + " ticks !");
                                    } else {
                                        playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "The weather is nice in " + otherworld.getName());
                                    }
                                } catch (NumberFormatException e) {
                                    playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Please enter an integer in the third argument !");
                                }
                            }
                        }
                    }
                }
                /// RAIN
                else if (args[0].equalsIgnoreCase("rain") && player.hasPermission("cryweather.rain")) {

                    if (args.length == 1) {
                        if (getConfig().getBoolean("rainEnabled")) {
                            world.setStorm(true);
                            world.setThundering(true);
                            playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is raining !");
                        } else {
                            playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Rain is disabled on this server");
                        }
                    } else if (args.length == 2 || args.length == 3) {

                        if (player.hasPermission("cryweather.rain.otherworld")) {
                            World otherworld = getServer().getWorld(args[1]);

                            if (otherworld == null) {
                                playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "This world is not available !");
                            } else {
                                try {
                                    if (getConfig().getBoolean("rainEnabled")) {
                                        otherworld.setStorm(true);
                                        otherworld.setThundering(true);
                                        if (args.length == 3) {
                                            otherworld.setWeatherDuration(Integer.parseInt(args[2]));
                                            playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is raining in " + otherworld.getName() + " during " + args[2] + " ticks !");
                                        } else {
                                            playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is raining in " + otherworld.getName());
                                        }

                                    } else
                                        playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Rain is disabled on this server");

                                } catch (NumberFormatException e) {
                                    playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Please enter an integer in the third argument !");
                                }
                            }
                        }
                    }

                }
                /// SET
                else if (args[0].equalsIgnoreCase("set") && player.hasPermission("cryweather.set")) {

                    if (args.length == 3) {
                        World otherworld = getServer().getWorld(args[1]);

                        if (otherworld == null)
                            playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "This world is not available !");
                        else {
                            try {
                                otherworld.setTime(Integer.parseInt(args[2]));
                                playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "Time set to " + args[2] + " in " + otherworld.getName() + " !");
                            } catch (NumberFormatException e) {
                                playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Please enter an integer in the second argument !");
                            }
                        }
                    } else {
                        playerMsg = ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Bad parameters";
                    }
                }

                /// WORLDSPEED
                else if (args[0].equalsIgnoreCase("worldspeed") && player.hasPermission("cryweather.worldspeed")) {

                    if (args.length == 2) {
                        try {
                            int speed = Integer.parseInt(args[1]);

                            if (speed <= 10 && speed >= -10) {
                                timeSpeed = speed;
                                getConfig().set("worldTimeSpeed", timeSpeed);
                                saveConfig();
                                playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "The world speed is up to " + timeSpeed);
                            } else
                                playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Speed must be between 10 and -10");
                        } catch (NumberFormatException e) {
                            playerMsg = (ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Please enter an integer in the second argument !");
                        }
                    } else {
                        playerMsg = ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Bad parameters";
                    }
                } else {
                    playerMsg = ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Missing params after /cw";
                }
            }
            player.sendMessage(playerMsg);
        }
        return true;
    }
}