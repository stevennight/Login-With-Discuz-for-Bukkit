package cn.sevaft.plugins.minecraft.loginwithdiscuz.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import cn.sevaft.plugins.minecraft.loginwithdiscuz.function.Common;
import cn.sevaft.plugins.minecraft.loginwithdiscuz.main.Loginwithdiscuz;
import cn.sevaft.plugins.minecraft.loginwithdiscuz.task.KickPlayerTask;
import cn.sevaft.plugins.minecraft.loginwithdiscuz.task.TipsLogin;

public class CommandLogout implements CommandExecutor {

	private final Plugin plugin;
	
    public CommandLogout(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	
    	Player player;
    	if(sender instanceof Player){
    		player = (Player)sender;
    	}else{
    		sender.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.error.mustplayer"));
    		return true;
    	}
    	
    	if(Loginwithdiscuz.loginuser.get(player.getName())){
        	
        	//�жϲ����������������
        	if(args.length==0){
        		
        	}else{
        		sender.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.error.argswrong"));
        		return false;
        	}
        	
        	//ִ����䡣
        	//��Ϊ��logout�ģ�����Ѿ��ֶ��˳���¼ ���Խ����Զ���¼��
    		Loginwithdiscuz.userConfig.getUserConfig().set("Users."+player.getName()+".LastIp", "0.0.0.0");
        	Loginwithdiscuz.userConfig.getUserConfig().set("Users."+player.getName()+".LogoutTime", 0);
        	//�˳��ص��¼
        	String world = player.getWorld().getName();
        	double posx = player.getLocation().getX();
        	double posy = player.getLocation().getY();
        	double posz = player.getLocation().getZ();
        	float posyaw = player.getLocation().getYaw();
        	float pospitch = player.getLocation().getPitch();
        	Loginwithdiscuz.userConfig.getUserConfig().set("Users."+player.getName()+".world",world);
        	Loginwithdiscuz.userConfig.getUserConfig().set("Users."+player.getName()+".posx",posx);
        	Loginwithdiscuz.userConfig.getUserConfig().set("Users."+player.getName()+".posy",posy);
        	Loginwithdiscuz.userConfig.getUserConfig().set("Users."+player.getName()+".posz",posz);
        	Loginwithdiscuz.userConfig.getUserConfig().set("Users."+player.getName()+".posyaw",posyaw);
        	Loginwithdiscuz.userConfig.getUserConfig().set("Users."+player.getName()+".pospitch",pospitch);
        	//����
        	Loginwithdiscuz.userConfig.saveUserConfig();
        	
        	//�����û�����¼�ص㡣�������ó�ʱ�ǳ���
        	//�̶���¼λ�á�
        	boolean openloginpoint = this.plugin.getConfig().getBoolean("config.loginpoint.open");
        	if(openloginpoint){
        		player.teleport(Common.getLoginPos(this.plugin,player));
        	}
        	//��ʱ��¼�߳����
        	int overtime=this.plugin.getConfig().getInt("config.overtime");
        	BukkitTask kickplayertask = new KickPlayerTask(player).runTaskLater(this.plugin,20*overtime);
        	Loginwithdiscuz.userkicktask.put(player.getName(), kickplayertask);
        	//��ʾ��ʾ
        	int tipstime=(int) this.plugin.getConfig().getInt("config.tipstime");
        	BukkitTask tipslogin = new TipsLogin(player,overtime).runTaskTimer(this.plugin, 0, 20*tipstime);
        	Loginwithdiscuz.usertipstask.put(player.getName(), tipslogin);
        	
        	//����û���¼״̬��Ϣ����ֹ�Զ���¼��
        	Loginwithdiscuz.loginuser.put(player.getName(),false);
        	sender.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.login.logoutsuccess"));
    	}else{
    		sender.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.error.nologin"));
    	}
    	
    	return true;
    }
}
