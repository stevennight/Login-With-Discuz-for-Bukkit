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
	
	public static HashMap<String, Boolean> loginuser = new HashMap<String, Boolean>();//玩家登录情况
	public static HashMap<String, BukkitTask> userkicktask = new HashMap<String, BukkitTask>();//玩家踢出计时器
	public static HashMap<String, BukkitTask> usertipstask = new HashMap<String, BukkitTask>();//玩家提示计时器
	
	public static ConfigLoadLanguage languageConfig;
	public static ConfigLoadUserInfo userConfig;
	
    @Override
    public void onEnable() {
    	
    	//配置文件
    	this.getConfig();
    	ConfigDefault.SetConfigDefault(this);
    	languageConfig = new ConfigLoadLanguage(this);
    	ConfigDefault.setLanguageDefault(languageConfig);
    	userConfig = new ConfigLoadUserInfo(this);
    	
    	PlayerLoginStatusReload.reload(this);
    	
    	//事件
    	new Event(this);
    	//指令
    	this.getCommand("login").setExecutor(new CommandLogin(this));
    	this.getCommand("logout").setExecutor(new CommandLogout(this));
    	this.getCommand("setloginlocal").setExecutor(new CommandSetLoginLocation(this));
    	
    	getLogger().info(languageConfig.getLanguageConfig().getString("language.console.enable"));
    }
    
    @Override
    public void onDisable() {
    	//保存配置文件
    	this.saveConfig();
    	languageConfig.saveLanguageConfig();
    	
    	//保存用户信息
    	Iterator<? extends Player> players = getServer().getOnlinePlayers().iterator();
    	while(players.hasNext()){
	    	//退出地点记录
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
    	//保存
    	Loginwithdiscuz.userConfig.saveUserConfig();
    	
    	getLogger().info(languageConfig.getLanguageConfig().getString("language.console.disable"));
    } 
}