package cn.sevaft.plugins.minecraft.loginwithdiscuz.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import cn.sevaft.plugins.minecraft.loginwithdiscuz.main.Loginwithdiscuz;
import cn.sevaft.plugins.minecraft.loginwithdiscuz.task.CommandLoginPasswordCheck;

public class CommandLogin implements CommandExecutor {

	private final Plugin plugin;
	
    public CommandLogin(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	
    	if(!Loginwithdiscuz.loginuser.get(sender.getName())){

    		//判断是否为玩家
        	if(sender instanceof Player){

        	}else{
        		sender.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.error.mustplayer"));
        		return true;
        	}
        	
        	//判断参数个数并处理参数
        	String passworduserinput;
        	if(args.length==1){
        		passworduserinput = args[0];
        	}else{
        		sender.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.error.argswrong"));
        		return false;
        	}
        	
        	//执行语句。
        	//执行密码查询
        	new CommandLoginPasswordCheck(this.plugin,(Player)sender,passworduserinput).runTask(this.plugin);
        	
    	}else{
    		sender.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.login.loginyet"));
    	}
    	
    	return true;
    }
}
