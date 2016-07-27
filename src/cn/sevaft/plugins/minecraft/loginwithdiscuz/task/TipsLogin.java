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
        // ����Ҫ�����е�ʱ��ִ�е����ݷ���
    	if(time == 0)
        {
           cancel();  // ��ֹ�߳�
             return;
        }
        player.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.login.tip"));
    }	
}
