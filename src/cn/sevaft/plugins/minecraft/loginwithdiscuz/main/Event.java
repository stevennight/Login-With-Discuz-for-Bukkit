package cn.sevaft.plugins.minecraft.loginwithdiscuz.main;

import java.util.Date;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import cn.sevaft.plugins.minecraft.loginwithdiscuz.function.Common;
import cn.sevaft.plugins.minecraft.loginwithdiscuz.task.KickPlayerTask;
import cn.sevaft.plugins.minecraft.loginwithdiscuz.task.TipsLogin;

public class Event implements Listener {
	
	private final Plugin plugin;
	
	Event(Plugin plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	//玩家event
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
    	Player player = event.getPlayer();
    	
    	//取消进入之后显示的黄色字体加入提示。
    	event.setJoinMessage("");
    	
    	//自动登录（免登录）
    	//无用户登录状态信息，禁止自动登录。
    	if(Loginwithdiscuz.loginuser.containsKey(player.getName())){
	    	int loginovertime=plugin.getConfig().getInt("config.autologin.overtime");
	    	if(loginovertime>0){//>0表示设置免登录，=0表示不设置免登录
	    		if(Loginwithdiscuz.userConfig.getUserConfig().isSet("Users."+player.getName()+".LastIp") &&
	    				Loginwithdiscuz.userConfig.getUserConfig().isSet("Users."+player.getName()+".LogoutTime")){
		    		String lastIp=Loginwithdiscuz.userConfig.getUserConfig().getString("Users."+player.getName()+".LastIp");
		    		long logouttime=Loginwithdiscuz.userConfig.getUserConfig().getLong("Users."+player.getName()+".LogoutTime");
		    		if(lastIp.equals(player.getAddress().getAddress().getHostAddress())){
		    			Date nowtime = new Date();
		    			if(nowtime.getTime()-logouttime<=(loginovertime*1000)){
		    				//设置登录变量(已登录)
		                	Loginwithdiscuz.loginuser.put(player.getName(),true);
		                	
		                	//登录提示。
		                	player.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.login.autologin"));
		                	String bcm_join=Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.login.joinboardcast");
							bcm_join=bcm_join.replace("{username}", player.getName());
							plugin.getServer().broadcastMessage(bcm_join);
							
							//不执行下面超时踢出等指令。不设置任务。
							return;
		    			}else{
		    				//设置登录变量(未登录)
		                	Loginwithdiscuz.loginuser.put(player.getName(),false);
		    			}
		    		}else{
		    			//设置登录变量(未登录)
		            	Loginwithdiscuz.loginuser.put(player.getName(),false);
		    		}
	    		}else{
	    			//设置登录变量(未登录)
	            	Loginwithdiscuz.loginuser.put(player.getName(),false);
	    		}
	    	}else{
	        	//设置登录变量(未登录)
	        	Loginwithdiscuz.loginuser.put(player.getName(),false);
	    	}
    	}
    	else{
    		//设置登录变量(未登录)
        	Loginwithdiscuz.loginuser.put(player.getName(),false);
    	}
    	
    	//固定登录位置。
    	boolean openloginpoint = this.plugin.getConfig().getBoolean("config.loginpoint.open");
    	if(openloginpoint){
    		player.teleport(Common.getLoginPos(this.plugin,player));
    	}
    	
    	//超时登录踢出玩家
    	int overtime=this.plugin.getConfig().getInt("config.overtime");
    	BukkitTask kickplayertask = new KickPlayerTask(player).runTaskLater(this.plugin,20*overtime);
    	Loginwithdiscuz.userkicktask.put(player.getName(), kickplayertask);
    	//显示提示
    	int tipstime=(int) this.plugin.getConfig().getInt("config.tipstime");
    	BukkitTask tipslogin = new TipsLogin(player,overtime).runTaskTimer(this.plugin, 0, 20*tipstime);
    	Loginwithdiscuz.usertipstask.put(player.getName(), tipslogin);
    	
    }
    
    //退出登录取消在线状态。
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event){
    	Player player = event.getPlayer();
    	
    	if(Loginwithdiscuz.loginuser.get(player.getName())){
    		//保存玩家退出时候的登录信息。(IP,退出时间)
    		Loginwithdiscuz.userConfig.getUserConfig().set("Users."+player.getName()+".LastIp", player.getAddress().getAddress().getHostAddress());
        	Date logouttime = new Date();
        	Loginwithdiscuz.userConfig.getUserConfig().set("Users."+player.getName()+".LogoutTime", logouttime.getTime());
        	//退出地点记录
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
        	//保存
        	Loginwithdiscuz.userConfig.saveUserConfig();
        	//设置用户为未登录状态
        	Loginwithdiscuz.loginuser.put(player.getName(),false);
    	}
    }
    
    //指令。
    @EventHandler
    public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event){
    	Player player = event.getPlayer();
    	String[] args = event.getMessage().split(" ");
    	if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
    		if(args[0].equals("/login")||args[0].equals("/logout")||args[0].equals("/lwd")){
    			
    		}else{
    			event.setCancelled(true);
    		}
    	}
    }
	
    //伤害取消
    @EventHandler(priority = EventPriority.HIGHEST)
    public void dmg(final EntityDamageEvent event) {
    	Entity e = event.getEntity();
    	if(e instanceof Player){
    		Player player = (Player)e;
    		if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
        		
        		event.setCancelled(true);
        	}
    	}
    	else{
    		
    	}
    }
    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
    	Player attacker;
    	if( event.getDamager() instanceof Player ) {
    		attacker = (Player)event.getDamager();
    		if(!Loginwithdiscuz.loginuser.get(attacker.getName())){
        		event.setCancelled(true);
        	}
    	}
    }
    
    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event){
    	Player player = event.getPlayer();
    	if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
    		
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onPlayerAchievementAwardedEvent(PlayerAchievementAwardedEvent event){
    	Player player = event.getPlayer();
    	if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
    		
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onPlayerAnimationEvent(PlayerAnimationEvent event){
    	Player player = event.getPlayer();
    	if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
    		
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onPlayerBedEnterEvent(PlayerBedEnterEvent event){
    	Player player = event.getPlayer();
    	if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
    		
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent event){
    	Player player = event.getPlayer();
    	if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
    		
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onPlayerEditBookEvent(PlayerEditBookEvent event){
    	Player player = event.getPlayer();
    	if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
    		
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onPlayerFishEvent(PlayerFishEvent event){
    	Player player = event.getPlayer();
    	if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
    		
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onPlayerGameModeChangeEvent(PlayerGameModeChangeEvent event){
    	Player player = event.getPlayer();
    	if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
    		
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event){
    	Player player = event.getPlayer();
    	if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
    		
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event){
    	Player player = event.getPlayer();
    	if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
    		
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onPlayerItemConsumeEvent(PlayerItemConsumeEvent event){
    	Player player = event.getPlayer();
    	if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
    		
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onPlayerItemHeldEvent(PlayerItemHeldEvent event){
    	Player player = event.getPlayer();
    	if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
    		
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event){
    	Player player = event.getPlayer();
    	if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
    		
    		event.setCancelled(true);
    	}
    }

    @EventHandler
    public void onPlayerPickupItemEvent(PlayerPickupItemEvent event){
    	Player player = event.getPlayer();
    	if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
    		
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onPlayerShearEntityEvent(PlayerShearEntityEvent event){
    	Player player = event.getPlayer();
    	if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
    		
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onPlayerStatisticIncrementEvent(PlayerStatisticIncrementEvent event){
    	Player player = event.getPlayer();
    	if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
    		
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onPlayerToggleFlightEvent(PlayerToggleFlightEvent event){
    	Player player = event.getPlayer();
    	if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
    		
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent event){
    	Player player = event.getPlayer();
    	if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
    		
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onPlayerToggleSprintEvent(PlayerToggleSprintEvent event){
    	Player player = event.getPlayer();
    	if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
    		
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onPlayerUnleashEntityEvent(PlayerUnleashEntityEvent event){
    	Player player = event.getPlayer();
    	if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
    		
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onPlayerVelocityEvent(PlayerVelocityEvent event){
    	Player player = event.getPlayer();
    	if(!Loginwithdiscuz.loginuser.containsKey(player.getName())||!Loginwithdiscuz.loginuser.get(player.getName())){
    		
    		event.setCancelled(true);
    	}
    }
}
