package cn.sevaft.plugins.minecraft.loginwithdiscuz.task;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import cn.sevaft.plugins.minecraft.loginwithdiscuz.main.Loginwithdiscuz;

public class TipsLogin extends BukkitRunnable {
	 
    private final Player player;
    int time;
 
    public TipsLogin(Player player,int time) {
        this.player = player;
        this.time = time;
    }
 
    @Override
    public void run() {
        // 你需要在运行的时候执行的内容放这
    	if(time == 0)
        {
           cancel();  // 终止线程
             return;
        }
        player.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.login.tip"));
    }	
}
