package cn.sevaft.plugins.minecraft.loginwithdiscuz.main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigLoadUserInfo {
	
	private FileConfiguration userConfig = null;
	private File userFile = null;
	Plugin plugin;
	
	ConfigLoadUserInfo(Plugin plugin){
		this.plugin = plugin;
		reloadUserInfo();
	}
	
	public void reloadUserInfo() {
	    if (this.userFile == null) {
	    	this.userFile = new File(this.plugin.getDataFolder(), "/save/users.yml");
	    }
	    this.userConfig = YamlConfiguration.loadConfiguration(userFile);
	}
	
	public FileConfiguration getUserConfig() {
	    if (this.userConfig == null) {
	    	reloadUserInfo();
	    }
	    return this.userConfig;
	}
	
	public void saveUserConfig() {
	    if (this.userConfig == null || this.userFile == null) {
	    	this.plugin.getLogger().log(Level.SEVERE, "Language config load failed, so you can't save it.");
	        return;
	    }
	    try {
	    	getUserConfig().save(this.userFile);
	    } catch (IOException ex) {
	        this.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.userFile, ex);
	    }
	}
}
