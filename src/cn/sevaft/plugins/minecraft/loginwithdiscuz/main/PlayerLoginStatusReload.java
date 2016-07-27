package cn.sevaft.plugins.minecraft.loginwithdiscuz.main;

import java.util.Collection;
import java.util.Iterator;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import cn.sevaft.plugins.minecraft.loginwithdiscuz.task.KickPlayerTask;
import cn.sevaft.plugins.minecraft.loginwithdiscuz.task.TipsLogin;

public class PlayerLoginStatusReload {

	public static void reload(Plugin plugin){
		Collection<? extends Player> onlinePlayers=plugin.getServer().getOnlinePlayers();
		Iterator<? extends Player> onlinePlayersIterator = onlinePlayers.iterator();
		while(onlinePlayersIterator.hasNext()){
			Player player = onlinePlayersIterator.next();
			Loginwithdiscuz.loginuser.put(player.getName(), false);
			
	    	//超时登录踢出玩家
	    	int overtime=plugin.getConfig().getInt("config.overtime");
	    	BukkitTask kickplayertask = new KickPlayerTask(player).runTaskLater(plugin,20*overtime);
	    	Loginwithdiscuz.userkicktask.put(player.getName(), kickplayertask);
	    	//显示提示
	    	int tipstime=plugin.getConfig().getInt("config.tipstime");
	    	BukkitTask tipslogin = new TipsLogin(player,overtime).runTaskTimer(plugin, 0, 20*tipstime);
	    	Loginwithdiscuz.usertipstask.put(player.getName(), tipslogin);
		}
	}
	
}
