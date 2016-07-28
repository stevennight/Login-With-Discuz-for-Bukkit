package cn.sevaft.plugins.minecraft.loginwithdiscuz.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import cn.sevaft.plugins.minecraft.loginwithdiscuz.main.Loginwithdiscuz;

public class CommandLWD implements CommandExecutor {

	private final Plugin plugin;
	
    public CommandLWD(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

    	//判断参数个数并处理参数
    	String type=null;
    	if(args.length==1){
    		type = args[0];
    	}else{
    		type="help";
    	}
    	
		//判断是否为玩家
    	if(sender instanceof Player){
    		if(type.equals("help")){
    			playerhelp((Player)sender);
    		}else if(type.equals("reload")){
    			playerreload((Player)sender);
    		}else if(type.equals("saveconfig")){
    			playersaveconfig((Player)sender);
    		}
    	}else{
    		if(type.equals("help")){
    			String message = Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.help.console");
    			String[] messages = message.split("\\{newline\\}");
    			sender.sendMessage(messages);
    		}else if(type.equals("reload")){
    			this.plugin.reloadConfig();
        		Loginwithdiscuz.languageConfig.reloadLanguage();
        		Loginwithdiscuz.userConfig.reloadUserInfo();
        		sender.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.console.reloadsuccess"));
    		}else if(type.equals("saveconfig")){
    			this.plugin.saveConfig();
        		Loginwithdiscuz.languageConfig.saveLanguageConfig();
        		Loginwithdiscuz.userConfig.saveUserConfig();
        		sender.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.console.reloadsuccess"));
    		}
    	}
    	
    	return true;
    }
    
    private void playerhelp(Player player){
    	if(player.isOp() && Loginwithdiscuz.loginuser.get(player.getName())){
    		String message = Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.help.admin");
    		String[] messages = message.split("\\{newline\\}");
    		player.sendMessage(messages);
    	}else{
    		String message = Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.help.player");
    		String[] messages = message.split("\\{newline\\}");
    	    player.sendMessage(messages);
    	}
    }
    
    private void playerreload(Player player){
    	if(player.isOp() && Loginwithdiscuz.loginuser.get(player.getName())){
    		this.plugin.reloadConfig();
    		Loginwithdiscuz.languageConfig.reloadLanguage();
    		Loginwithdiscuz.userConfig.reloadUserInfo();
    		player.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.admin.reloadsuccess"));
    	}else{
    		
    	}
    }
    
    private void playersaveconfig(Player player){
    	if(player.isOp() && Loginwithdiscuz.loginuser.get(player.getName())){
    		this.plugin.saveConfig();
    		Loginwithdiscuz.languageConfig.saveLanguageConfig();
    		Loginwithdiscuz.userConfig.saveUserConfig();
    		player.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.admin.saveconfigsuccess"));
    	}else{
    		
    	}
    }
}
