package cn.sevaft.plugins.minecraft.loginwithdiscuz.task;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class KickPlayerTask extends BukkitRunnable {
	 
	    private final Player player;
	 
	    public KickPlayerTask(Player player) {
	        this.player = player;
	    }
	 
	    @Override
	    public void run() {
	        // 你需要在运行的时候执行的内容放这
	        player.kickPlayer("登录超时被踢出。");
	    }	
}
