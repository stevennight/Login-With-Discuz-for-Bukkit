package cn.sevaft.plugins.minecraft.loginwithdiscuz.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import cn.sevaft.plugins.minecraft.loginwithdiscuz.main.Loginwithdiscuz;

public class CommandSetLoginLocation implements CommandExecutor {

	private final Plugin plugin;
	
    public CommandSetLoginLocation(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	
    	Player player;
    	if(sender instanceof Player){
    		player = (Player)sender;
    		if(player.isOp()){
            	
    		}else{
    			return true;
    		}
    	}else{
    		sender.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.error.mustplayer"));
    		return true;
    	}
    	
    	if(Loginwithdiscuz.loginuser.get(player.getName())){
        	
        	//判断参数个数并处理参数
        	if(args.length>=0&&args.length<=1){
        		//执行语句。
            	//执行登录位置设置。
            	String world = player.getWorld().getName();
        		double posx = player.getLocation().getX();
        		double posy = player.getLocation().getY();
        		double posz = player.getLocation().getZ();
        		float posyaw = player.getLocation().getYaw();
        		float pospitch = player.getLocation().getPitch();
        		if(args.length == 1){
        			if(args[0].equals("default")){
        				plugin.getConfig().set("config.loginpoint.defaultworld", world);
        			}
        		}
        		plugin.getConfig().set("config.loginpoint."+world+".posx", posx);
    			plugin.getConfig().set("config.loginpoint."+world+".posy", posy);
    			plugin.getConfig().set("config.loginpoint."+world+".posz", posz);
    			plugin.getConfig().set("config.loginpoint."+world+".posyaw", posyaw);
    			plugin.getConfig().set("config.loginpoint."+world+".pospitch", pospitch);
    			plugin.saveConfig();
    			
    			String successtips = Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.admin.setloginlocationsuccess");
    			successtips = successtips.replace("{world}",world);
    			player.sendMessage(successtips);
    			return true;
        	}else{
        		sender.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.error.argswrong"));
        		return false;
        	}    		
    	}
    	
    	return true;
    }
}
