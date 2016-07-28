package cn.sevaft.plugins.minecraft.loginwithdiscuz.function;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import cn.sevaft.plugins.minecraft.loginwithdiscuz.main.Loginwithdiscuz;

public class Common {
	
	public static Location getLoginPos(Plugin plugin,Player player){
		String loginworldstring=null;
		boolean issetconfig=Loginwithdiscuz.userConfig.getUserConfig().isSet("Users."+player.getName()+".posx")&&
				Loginwithdiscuz.userConfig.getUserConfig().isSet("Users."+player.getName()+".posy")&&
				Loginwithdiscuz.userConfig.getUserConfig().isSet("Users."+player.getName()+".posz")&&
				Loginwithdiscuz.userConfig.getUserConfig().isSet("Users."+player.getName()+".posyaw")&&
				Loginwithdiscuz.userConfig.getUserConfig().isSet("Users."+player.getName()+".pospitch");
		if(issetconfig){
			loginworldstring = Loginwithdiscuz.userConfig.getUserConfig().getString("Users."+player.getName()+".world");
			if(!plugin.getConfig().isSet("config.loginpoint."+loginworldstring)){
				loginworldstring = plugin.getConfig().getString("config.loginpoint.defaultworld");
			}
		}else{
			loginworldstring = plugin.getConfig().getString("config.loginpoint.defaultworld");
		}
		World world = plugin.getServer().getWorld(loginworldstring);
		Location loc = new Location(world,
				plugin.getConfig().getDouble("config.loginpoint."+loginworldstring+".posx"),
				plugin.getConfig().getDouble("config.loginpoint."+loginworldstring+".posy"),
				plugin.getConfig().getDouble("config.loginpoint."+loginworldstring+".posz"),
				(float)plugin.getConfig().getDouble("config.loginpoint."+loginworldstring+".posyaw"),
				(float)plugin.getConfig().getDouble("config.loginpoint."+loginworldstring+".pospitch"));
		return loc;
	}
	
	public static Location getPlayerLastpos(Plugin plugin,Player player){
		Location loc=null;
		boolean issetconfig=Loginwithdiscuz.userConfig.getUserConfig().isSet("Users."+player.getName()+".world")&&
				Loginwithdiscuz.userConfig.getUserConfig().isSet("Users."+player.getName()+".posx")&&
				Loginwithdiscuz.userConfig.getUserConfig().isSet("Users."+player.getName()+".posy")&&
				Loginwithdiscuz.userConfig.getUserConfig().isSet("Users."+player.getName()+".posz")&&
				Loginwithdiscuz.userConfig.getUserConfig().isSet("Users."+player.getName()+".posyaw")&&
				Loginwithdiscuz.userConfig.getUserConfig().isSet("Users."+player.getName()+".pospitch");
		if(issetconfig){
			World world = plugin.getServer().getWorld(
					Loginwithdiscuz.userConfig.getUserConfig().getString("Users."+player.getName()+".world"));
    		double posx = Loginwithdiscuz.userConfig.getUserConfig().getDouble("Users."+player.getName()+".posx");
    		double posy = Loginwithdiscuz.userConfig.getUserConfig().getDouble("Users."+player.getName()+".posy");
    		double posz = Loginwithdiscuz.userConfig.getUserConfig().getDouble("Users."+player.getName()+".posz");
    		float posyaw = (float) Loginwithdiscuz.userConfig.getUserConfig().getDouble("Users."+player.getName()+".posyaw");
    		float pospitch = (float) Loginwithdiscuz.userConfig.getUserConfig().getDouble("Users."+player.getName()+".pospitch");
    		loc = new Location(world,posx,posy,posz,posyaw,pospitch);
		}else{
			//没有选项直接传送到世界出生点。
			String worldstring = plugin.getConfig().getString("config.loginpoint.defaultworld");
			loc = plugin.getServer().getWorld(worldstring).getSpawnLocation();
		}
		return loc;
	}
	
}
