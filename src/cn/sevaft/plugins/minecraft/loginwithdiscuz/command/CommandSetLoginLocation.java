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
    	
    	if(Loginwithdiscuz.loginuser.get(sender.getName())){

    		//判断是否为玩家
    		Player player;
        	if(sender instanceof Player){
        		if(sender.isOp()){
                	player = (Player)sender;
        		}else{
        			return false;
        		}
        	}else{
        		sender.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.error.mustplayer"));
        		return true;
        	}
        	
        	//判断参数个数并处理参数
        	if(args.length==0){
        		
        	}else{
        		sender.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.error.argswrong"));
        		return false;
        	}
        	
        	//执行语句。
        	//执行登录位置设置。
        	String world = player.getWorld().getName();
    		double posx = player.getLocation().getX();
    		double posy = player.getLocation().getY();
    		double posz = player.getLocation().getZ();
    		float posyaw = player.getLocation().getYaw();
    		float pospitch = player.getLocation().getPitch();
    		plugin.getConfig().set("config.loginpoint.world", world);
			plugin.getConfig().set("config.loginpoint.posx", posx);
			plugin.getConfig().set("config.loginpoint.posy", posy);
			plugin.getConfig().set("config.loginpoint.posz", posz);
			plugin.getConfig().set("config.loginpoint.posyaw", posyaw);
			plugin.getConfig().set("config.loginpoint.pospitch", pospitch);
			plugin.saveConfig();
			player.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.admin.setloginlocationsuccess"));
			return true;
        	
    	}
    	
    	return true;
    }
}
