package cn.sevaft.plugins.minecraft.loginwithdiscuz.main;

import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import cn.sevaft.plugins.minecraft.loginwithdiscuz.command.CommandLogin;
import cn.sevaft.plugins.minecraft.loginwithdiscuz.command.CommandLogout;
import cn.sevaft.plugins.minecraft.loginwithdiscuz.command.CommandSetLoginLocation;

public final class Loginwithdiscuz extends JavaPlugin {
	
	public static HashMap<String, Boolean> loginuser = new HashMap<String, Boolean>();//��ҵ�¼���
	public static HashMap<String, BukkitTask> userkicktask = new HashMap<String, BukkitTask>();//����߳���ʱ��
	public static HashMap<String, BukkitTask> usertipstask = new HashMap<String, BukkitTask>();//�����ʾ��ʱ��
	
	public static ConfigLoadLanguage languageConfig;
	public static ConfigLoadUserInfo userConfig;
	
    @Override
    public void onEnable() {
    	
    	//�����ļ�
    	this.getConfig();
    	ConfigDefault.SetConfigDefault(this);
    	languageConfig = new ConfigLoadLanguage(this);
    	ConfigDefault.setLanguageDefault(languageConfig);
    	userConfig = new ConfigLoadUserInfo(this);
    	
    	PlayerLoginStatusReload.reload(this);
    	
    	//�¼�
    	new Event(this);
    	//ָ��
    	this.getCommand("login").setExecutor(new CommandLogin(this));
    	this.getCommand("logout").setExecutor(new CommandLogout(this));
    	this.getCommand("setloginlocal").setExecutor(new CommandSetLoginLocation(this));
    	
    	getLogger().info(languageConfig.getLanguageConfig().getString("language.console.enable"));
    }
    
    @Override
    public void onDisable() {
    	//���������ļ�
    	this.saveConfig();
    	languageConfig.saveLanguageConfig();
    	
    	//�����û���Ϣ
    	Iterator<? extends Player> players = getServer().getOnlinePlayers().iterator();
    	while(players.hasNext()){
	    	//�˳��ص��¼
    		Player player = players.next();
    		if(loginuser.containsKey(player.getName())&&loginuser.get(player.getName())){
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
    		}
    	}
    	//����
    	Loginwithdiscuz.userConfig.saveUserConfig();
    	
    	getLogger().info(languageConfig.getLanguageConfig().getString("language.console.disable"));
    } 
}