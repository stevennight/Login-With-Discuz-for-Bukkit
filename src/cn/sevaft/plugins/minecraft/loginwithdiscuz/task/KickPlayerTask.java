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
	        // ����Ҫ�����е�ʱ��ִ�е����ݷ���
	        player.kickPlayer("��¼��ʱ���߳���");
	    }	
}
