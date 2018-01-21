package me.crylonz;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class CryWeather extends JavaPlugin implements Listener {
	
	public final Logger log = Logger.getLogger("Minecraft");
	  
	  public void onEnable()
	  {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
		this.log.info("[CryWeather] is enabled !");
	    saveDefaultConfig();
	  }
	  
	  public void onDisable()
	  {
	    this.log.info("[CryWeather] is disabled !");
	  }
	  
	  @EventHandler
	  public void onWeatherChange(WeatherChangeEvent e)
	  {
	    if (e.toWeatherState() && !getConfig().getBoolean("rainEnabled"))
	      e.setCancelled(true);
	  }
	  
	  public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args)
	  {
	    World world = null;
	    Player player = null;
	    if ((sender instanceof Player))
	    {
	      player = (Player)sender;
	      world = player.getWorld();
	    }
	    
	    if (args.length == 0)
	    {
	      if (cmd.getName().equalsIgnoreCase("cenablerain")) {
	        if (player == null)
	        {
	          sender.sendMessage("this command can only be run by a player");
	        }
	        else if (player.hasPermission("cryweather.enablerain"))
	        {
	          getConfig().set("rainEnabled", true);
	          saveConfig();
	          
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "Rain is now enabled on the server");
	        }
	        else
	        {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	      if (cmd.getName().equalsIgnoreCase("cdisablerain")) {
		        if (player == null)
		        {
		          sender.sendMessage("this command can only be run by a player");
		        }
		        else if (player.hasPermission("cryweather.disablerain"))
		        {
		          getConfig().set("rainEnabled", false);
		          saveConfig();
		          world.setThundering(false);
		          world.setStorm(false);
		          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "Rain is now disabled on the server");
		        }
		        else
		        {
		          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
		        }
		      }
	    }
	    else
	    {
	    	player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Too many arguments !");
	    }
	    	
	    if (args.length == 0)
	    {
	      if (cmd.getName().equalsIgnoreCase("cday")) {
	        if (player == null)
	        {
	          sender.sendMessage("this command can only be run by a player");
	        }
	        else if (player.hasPermission("cryweather.day"))
	        {
	          world.setTime(0L);
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is day !");
	        }
	        else
	        {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	    }
	    else if (args.length > 1)
	    {
	      if (cmd.getName().equalsIgnoreCase("cday")) {
	        if (player == null) {
	          sender.sendMessage("this command can only be run by a player");
	        } else if (player.hasPermission("cryweather.day")) {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Too many arguments !");
	        } else {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	    }
	    else if (args.length == 1) {
	      if (cmd.getName().equalsIgnoreCase("cday"))
	      {
	        World otherworld = getServer().getWorld(args[0]);
	        if (player == null)
	        {
	          sender.sendMessage("this command can only be run by a player");
	        }
	        else if (otherworld == null)
	        {
	          if (player.hasPermission("cryweather.day")) {
	            player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "This world is not available !");
	          } else {
	            player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	          }
	        }
	        else if (player.hasPermission("cryweather.day"))
	        {
	          otherworld.setTime(0L);
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is day in " + otherworld.getName() + " !");
	        }
	        else
	        {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	    }
	    if (args.length == 0)
	    {
	      if (cmd.getName().equalsIgnoreCase("cnight")) {
	        if (player == null)
	        {
	          sender.sendMessage("this command can only be run by a player");
	        }
	        else if (player.hasPermission("cryweather.night"))
	        {
	          world.setTime(14000L);
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is night !");
	        }
	        else
	        {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	    }
	    else if (args.length > 1)
	    {
	      if (cmd.getName().equalsIgnoreCase("cnight")) {
	        if (player == null) {
	          sender.sendMessage("this command can only be run by a player");
	        } else if (player.hasPermission("cryweather.night")) {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Too many arguments !");
	        } else {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	    }
	    else if ((args.length == 1) && 
	      (cmd.getName().equalsIgnoreCase("cnight")))
	    {
	      World otherworld = getServer().getWorld(args[0]);
	      if (player == null)
	      {
	        sender.sendMessage("this command can only be run by a player");
	      }
	      else if (otherworld == null)
	      {
	        if (player.hasPermission("cryweather.night")) {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "This world is not available !");
	        } else {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	      else if (player.hasPermission("cryweather.night"))
	      {
	        otherworld.setTime(14000L);
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is night in " + otherworld.getName() + " !");
	      }
	      else
	      {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	      }
	    }
	    if (args.length == 0)
	    {
	      if (cmd.getName().equalsIgnoreCase("cmorning")) {
	        if (player == null)
	        {
	          sender.sendMessage("this command can only be run by a player");
	        }
	        else if (player.hasPermission("cryweather.morning"))
	        {
	          world.setTime(23000L);
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is morning !");
	        }
	        else
	        {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	    }
	    else if (args.length > 1)
	    {
	      if (cmd.getName().equalsIgnoreCase("cmorning")) {
	        if (player == null) {
	          sender.sendMessage("this command can only be run by a player");
	        } else if (player.hasPermission("cryweather.morning")) {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Too many arguments !");
	        } else {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	    }
	    else if ((args.length == 1) && 
	      (cmd.getName().equalsIgnoreCase("cmorning")))
	    {
	      World otherworld = getServer().getWorld(args[0]);
	      if (player == null)
	      {
	        sender.sendMessage("this command can only be run by a player");
	      }
	      else if (otherworld == null)
	      {
	        if (player.hasPermission("cryweather.morning")) {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "This world is not available !");
	        } else {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	      else if (player.hasPermission("cryweather.morning"))
	      {
	        otherworld.setTime(23000L);
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is morning in " + otherworld.getName() + " !");
	      }
	      else
	      {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	      }
	    }
	    if (args.length == 0)
	    {
	      if (cmd.getName().equalsIgnoreCase("cafternoon")) {
	        if (player == null)
	        {
	          sender.sendMessage("this command can only be run by a player");
	        }
	        else if (player.hasPermission("cryweather.afternoon"))
	        {
	          world.setTime(6500L);
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is afternoon !");
	        }
	        else
	        {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	    }
	    else if (args.length > 1)
	    {
	      if (cmd.getName().equalsIgnoreCase("cafternoon")) {
	        if (player == null) {
	          sender.sendMessage("this command can only be run by a player");
	        } else if (player.hasPermission("cryweather.afternoon")) {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Too many arguments !");
	        } else {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	    }
	    else if ((args.length == 1) && 
	      (cmd.getName().equalsIgnoreCase("cafternoon")))
	    {
	      World otherworld = getServer().getWorld(args[0]);
	      if (player == null)
	      {
	        sender.sendMessage("this command can only be run by a player");
	      }
	      else if (otherworld == null)
	      {
	        if (player.hasPermission("cryweather.afternoon")) {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "This world is not available !");
	        } else {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	      else if (player.hasPermission("cryweather.afternoon"))
	      {
	        otherworld.setTime(6500L);
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is afternoon in " + otherworld.getName() + " !");
	      }
	      else
	      {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	      }
	    }
	    if (args.length == 0)
	    {
	      if (cmd.getName().equalsIgnoreCase("cevening")) {
	        if (player == null)
	        {
	          sender.sendMessage("this command can only be run by a player");
	        }
	        else if (player.hasPermission("cryweather.evening"))
	        {
	          world.setTime(13000L);
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is evening !");
	        }
	        else
	        {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	    }
	    else if (args.length > 1)
	    {
	      if (cmd.getName().equalsIgnoreCase("cevening")) {
	        if (player == null) {
	          sender.sendMessage("this command can only be run by a player");
	        } else if (player.hasPermission("cryweather.evening")) {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Too many arguments !");
	        } else {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	    }
	    else if ((args.length == 1) && 
	      (cmd.getName().equalsIgnoreCase("cevening")))
	    {
	      World otherworld = getServer().getWorld(args[0]);
	      if (player == null)
	      {
	        sender.sendMessage("this command can only be run by a player");
	      }
	      else if (otherworld == null)
	      {
	        if (player.hasPermission("cryweather.evening")) {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "This world is not available !");
	        } else {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	      else if (player.hasPermission("cryweather.evening"))
	      {
	        otherworld.setTime(13000L);
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is evening in " + otherworld.getName() + " !");
	      }
	      else
	      {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	      }
	    }
	    if (args.length == 0)
	    {
	      if (cmd.getName().equalsIgnoreCase("csun")) {
	        if (player == null)
	        {
	          sender.sendMessage("this command can only be run by a player");
	        }
	        else if (player.hasPermission("cryweather.sun"))
	        {
	          world.setThundering(false);
	          world.setStorm(false);
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "The weather is nice !");
	        }
	        else
	        {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	    }
	    else if (args.length > 2)
	    {
	      if (cmd.getName().equalsIgnoreCase("csun")) {
	        if (player == null) {
	          sender.sendMessage("this command can only be run by a player");
	        } else if (player.hasPermission("cryweather.sun")) {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Too many arguments !");
	        } else {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	    }
	    else if ((args.length == 1) && 
	      (cmd.getName().equalsIgnoreCase("csun")))
	    {
	      World otherworld = getServer().getWorld(args[0]);
	      if (player == null)
	      {
	        sender.sendMessage("this command can only be run by a player");
	      }
	      else if (otherworld == null)
	      {
	        if (player.hasPermission("cryweather.sun")) {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "This world is not available !");
	        } else {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	      else if (player.hasPermission("cryweather.sun"))
	      {
	        world.setThundering(false);
	        world.setStorm(false);
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "The weather is nice in " + otherworld.getName() + " !");
	      }
	      else
	      {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	      }
	    }
	    if ((args.length == 2) && 
	      (cmd.getName().equalsIgnoreCase("csun")))
	    {
	      World otherworld = getServer().getWorld(args[0]);
	      if (player == null) {
	        sender.sendMessage("this command can only be run by a player");
	      } else if (otherworld == null)
	      {
	        if (player.hasPermission("cryweather.sun")) {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "This world is not available !");
	        } else {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	      else if (player.hasPermission("cryweather.sun")) {
	        try
	        {
	          world.setThundering(false);
	          world.setStorm(false);
	          world.setWeatherDuration(Integer.parseInt(args[1]));
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "The weather is nice in " + otherworld.getName() + " during " + args[1] + " ticks !");
	        }
	        catch (NumberFormatException e)
	        {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Please enter an integer in the second argument !");
	        }
	      } else {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	      }
	    }
	    /****** CRAIN ******/
	    if (args.length == 0)
	    {
	      if (cmd.getName().equalsIgnoreCase("crain")) {
	        if (player == null)
	        {
	          sender.sendMessage("this command can only be run by a player");
	        }
	        else if (player.hasPermission("cryweather.rain"))
	        {
	          if(getConfig().getBoolean("rainEnabled"))
	          {
	        	  world.setThundering(true);
		          world.setStorm(true);
		          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is raining !");
	          }
	          else
	          {
	        	  player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Rain is disabled on this server");  
	          }
	        }
	        else
	        {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	    }
	    else if (args.length > 2)
	    {
	      if (cmd.getName().equalsIgnoreCase("crain")) {
	        if (player == null) {
	          sender.sendMessage("this command can only be run by a player");
	        } else if (player.hasPermission("cryweather.rain")) {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Too many arguments !");
	        } else {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	    }
	    else if ((args.length == 1) && (cmd.getName().equalsIgnoreCase("crain")))
	    {
	      World otherworld = getServer().getWorld(args[0]);
	      if (player == null)
	      {
	        sender.sendMessage("this command can only be run by a player");
	      }
	      else if (otherworld == null)
	      {
	        if (player.hasPermission("cryweather.rain")) {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "This world is not available !");
	        } else {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	      else if (player.hasPermission("cryweather.rain"))
	      {
	    	  if(getConfig().getBoolean("rainEnabled"))
	          {
	        	  world.setThundering(true);
		          world.setStorm(true);
		          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is raining in " + otherworld.getName() + " !");
	          }
	          else
	          {
	        	  player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Rain is disabled on this server");  
	          }
	      }
	      else
	      {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	      }
	    }
	    if ((args.length == 2) && 
	      (cmd.getName().equalsIgnoreCase("crain")))
	    {
	      World otherworld = getServer().getWorld(args[0]);
	      if (player == null) {
	        sender.sendMessage("this command can only be run by a player");
	      } else if (otherworld == null)
	      {
	        if (player.hasPermission("cryweather.rain")) {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "This world is not available !");
	        } else {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	      else if (player.hasPermission("cryweather.rain")) {
	        try
	        {
	          if(getConfig().getBoolean("rainEnabled"))
		      {
	        	  world.setThundering(true);
	        	  world.setStorm(true);
	        	  world.setWeatherDuration(Integer.parseInt(args[1]));
	        	  player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is raining in " + otherworld.getName() + " during " + args[1] + " ticks !");
		      }
	          else
	          {
	        	  player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Rain is disabled on this server");
	          }
		    }
	        catch (NumberFormatException e)
	        {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Please enter an integer in the second argument !");
	        }
	      } else {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	      }
	    }
	    if ((args.length == 0) && 
	      (cmd.getName().equalsIgnoreCase("chelp"))) {
	      if (player == null)
	      {
	        sender.sendMessage("this command can only be run by a player");
	      }
	      else if (player.hasPermission("cryweather.help"))
	      {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "cday [World] " + ChatColor.WHITE + "Enabled day");
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "cnight [World] " + ChatColor.WHITE + "Enabled night");
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "cmorning [World] " + ChatColor.WHITE + "Enabled morning");
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "cafternoon [World] " + ChatColor.WHITE + "Enabled afternoon");
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "cevening [World] " + ChatColor.WHITE + "Enabled evening");
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "csun [World] [Duration] " + ChatColor.WHITE + "Enabled sun");
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "crain [World] [Duration] " + ChatColor.WHITE + "Enabled rain");
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "chelp " + ChatColor.WHITE + "Enabled help");
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "cset [Time] " + ChatColor.WHITE + "Set the time");
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "cweather" + ChatColor.WHITE + " Show version of the plugin");
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "wcday" + ChatColor.WHITE + " Enable day in the current world");
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "wcnight" + ChatColor.WHITE + " Enable night in the current world");
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "wcsun" + ChatColor.WHITE + " Enable sun in the current world");
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "wcrain" + ChatColor.WHITE + " Enable rain in the current world");
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "cEnableRain" + ChatColor.WHITE + " Allow rain on the server");
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "cDisableRain" + ChatColor.WHITE + " Disable rain on the server");
	      }
	      else
	      {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	      }
	    }
	    if ((args.length > 0) && 
	      (cmd.getName().equalsIgnoreCase("chelp"))) {
	      if (player == null) {
	        sender.sendMessage("this command can only be run by a player");
	      } else if (player.hasPermission("cryweather.help")) {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Too many arguments !");
	      } else {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	      }
	    }
	    if ((args.length < 2) && 
	      (cmd.getName().equalsIgnoreCase("cset"))) {
	      if (player == null) {
	        sender.sendMessage("this command can only be run by a player");
	      } else if (player.hasPermission("cryweather.set")) {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Not enough argument");
	      } else {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	      }
	    }
	    if (args.length == 2)
	    {
	      if (cmd.getName().equalsIgnoreCase("cset"))
	      {
	        World otherworld = getServer().getWorld(args[0]);
	        if (player == null) {
	          sender.sendMessage("this command can only be run by a player");
	        } else if (otherworld == null)
	        {
	          if (player.hasPermission("cryweather.set")) {
	            player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "This world is not available !");
	          } else {
	            player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	          }
	        }
	        else if (player.hasPermission("cryweather.set")) {
	          try
	          {
	            world.setTime(Integer.parseInt(args[1]));
	            player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "Time set to " + args[1] + " in " + otherworld.getName() + " !");
	          }
	          catch (NumberFormatException e)
	          {
	            player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Please enter an integer in the second argument !");
	          }
	        } else {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	    }
	    else if ((args.length > 2) && 
	      (cmd.getName().equalsIgnoreCase("cset"))) {
	      if (player == null) {
	        sender.sendMessage("this command can only be run by a player");
	      } else if (player.hasPermission("cryweather.set")) {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Too many arguments !");
	      } else {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	      }
	    }
	    if (args.length == 0)
	    {
	      if (cmd.getName().equalsIgnoreCase("cweather")) {
	        if (player == null) {
	          sender.sendMessage("this command can only be run by a player");
	        } else if (player.hasPermission("cryweather.cryweather")) {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "CryWeather Version 1.5 by Crylonz ");
	        } else {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	    }
	    else if ((args.length > 0) && 
	      (cmd.getName().equalsIgnoreCase("cweather"))) {
	      if (player == null) {
	        sender.sendMessage("this command can only be run by a player");
	      } else if (player.hasPermission("cryweather.cryweather")) {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Too many arguments !");
	      } else {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	      }
	    }
	    if (args.length == 0)
	    {
	      if (cmd.getName().equalsIgnoreCase("wcday")) {
	        if (player == null)
	        {
	          sender.sendMessage("this command can only be run by a player");
	        }
	        else if (player.hasPermission("cryweather.wcday"))
	        {
	          world.setTime(0L);
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is day !");
	        }
	        else
	        {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	    }
	    else if (args.length > 0) {
	      if (cmd.getName().equalsIgnoreCase("wcday")) {
	        if (player == null) {
	          sender.sendMessage("this command can only be run by a player");
	        } else if (player.hasPermission("cryweather.wcday")) {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Too many arguments !");
	        } else {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	    }
	    if (args.length == 0)
	    {
	      if (cmd.getName().equalsIgnoreCase("wcnight")) {
	        if (player == null)
	        {
	          sender.sendMessage("this command can only be run by a player");
	        }
	        else if (player.hasPermission("cryweather.wcnight"))
	        {
	          world.setTime(14000L);
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is night !");
	        }
	        else
	        {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	    }
	    else if ((args.length > 0) && 
	      (cmd.getName().equalsIgnoreCase("wcnight"))) {
	      if (player == null) {
	        sender.sendMessage("this command can only be run by a player");
	      } else if (player.hasPermission("cryweather.wcnight")) {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Too many arguments !");
	      } else {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	      }
	    }
	    if (args.length == 0)
	    {
	      if (cmd.getName().equalsIgnoreCase("wcrain")) {
	        if (player == null)
	        {
	          sender.sendMessage("this command can only be run by a player");
	        }
	        else if (player.hasPermission("cryweather.wcrain"))
	        {
	        	if(getConfig().getBoolean("rainEnabled"))
		          {
		        	  world.setThundering(true);
			          world.setStorm(true);
			          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "It is raining !");
		          }
		          else
		          {
		        	  player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Rain is disabled on this server");  
		          }
	        }
	        else
	        {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	    }
	    else if ((args.length > 0) && 
	      (cmd.getName().equalsIgnoreCase("wcrain"))) {
	      if (player == null) {
	        sender.sendMessage("this command can only be run by a player");
	      } else if (player.hasPermission("cryweather.wcrain")) {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Too many arguments !");
	      } else {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	      }
	    }
	    if (args.length == 0)
	    {
	      if (cmd.getName().equalsIgnoreCase("wcsun")) {
	        if (player == null)
	        {
	          sender.sendMessage("this command can only be run by a player");
	        }
	        else if (player.hasPermission("cryweather.wcsun"))
	        {
	          world.setThundering(false);
	          world.setStorm(false);
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.GREEN + "The weather is nice !");
	        }
	        else
	        {
	          player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	        }
	      }
	    }
	    else if ((args.length > 0) && 
	      (cmd.getName().equalsIgnoreCase("wcsun"))) {
	      if (player == null) {
	        sender.sendMessage("this command can only be run by a player");
	      } else if (player.hasPermission("cryweather.wcsun")) {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "Too many arguments !");
	      } else {
	        player.sendMessage(ChatColor.DARK_AQUA + "[CryWeather] " + ChatColor.RED + "You don't have permission !");
	      }
	    }
	    return true;
	  }

}
