package cn.sevaft.plugins.minecraft.loginwithdiscuz.main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigLoadLanguage {
	
	private FileConfiguration LanguageConfig = null;
	private File LanguageFile = null;
	Plugin plugin;
	
	ConfigLoadLanguage(Plugin plugin){
		this.plugin = plugin;
		reloadLanguage();
	}
	
	public void reloadLanguage() {
	    if (this.LanguageFile == null) {
	    	this.LanguageFile = new File(this.plugin.getDataFolder(), "language.yml");
	    }
	    this.LanguageConfig = YamlConfiguration.loadConfiguration(LanguageFile);
	}
	
	public FileConfiguration getLanguageConfig() {
	    if (this.LanguageConfig == null) {
	        reloadLanguage();
	    }
	    return this.LanguageConfig;
	}
	
	public void saveLanguageConfig() {
	    if (this.LanguageConfig == null || this.LanguageFile == null) {
	    	this.plugin.getLogger().log(Level.SEVERE, "Language config load failed, so you can't save it.");
	        return;
	    }
	    try {
	    	getLanguageConfig().save(this.LanguageFile);
	    } catch (IOException ex) {
	        this.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.LanguageFile, ex);
	    }
	}
}
