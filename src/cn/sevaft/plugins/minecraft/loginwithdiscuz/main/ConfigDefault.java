package cn.sevaft.plugins.minecraft.loginwithdiscuz.main;

import java.util.Iterator;

import org.bukkit.World;
import org.bukkit.plugin.Plugin;

public class ConfigDefault {
	
	public static void SetConfigDefault(Plugin plugin){
		//插件基础设置
		//登录超时时间
		if(!plugin.getConfig().isSet("config.overtime")){
			plugin.getConfig().set("config.overtime", 30);
		}
		//提示登录间隔时间
		if(!plugin.getConfig().isSet("config.tipstime")){
			plugin.getConfig().set("config.tipstime", 5);
		}
		//免登录
		if(!plugin.getConfig().isSet("config.autologin.overtime")){
			plugin.getConfig().set("config.autologin.overtime", 300);
		}
		//登录地点
		if(!plugin.getConfig().isSet("config.loginpoint.open")){
			plugin.getConfig().set("config.loginpoint.open", false);
		}
		if(!plugin.getConfig().isSet("config.loginpoint.world")){
			Iterator<World> worlds = plugin.getServer().getWorlds().iterator();
			String world=null;
			if(worlds.hasNext()){
				world = worlds.next().getName();
			}
			plugin.getConfig().set("config.loginpoint.world",world);
		}
		if(!plugin.getConfig().isSet("config.loginpoint.posx")){
			plugin.getConfig().set("config.loginpoint.posx", 0.0);
		}
		if(!plugin.getConfig().isSet("config.loginpoint.posy")){
			plugin.getConfig().set("config.loginpoint.posy", 80.0);
		}
		if(!plugin.getConfig().isSet("config.loginpoint.posz")){
			plugin.getConfig().set("config.loginpoint.posz", 0.0);
		}
		if(!plugin.getConfig().isSet("config.loginpoint.posyaw")){
			plugin.getConfig().set("config.loginpoint.posyaw", 0.0f);
		}
		if(!plugin.getConfig().isSet("config.loginpoint.pospitch")){
			plugin.getConfig().set("config.loginpoint.pospitch", 0.0f);
		}
		
		//数据库设置
		if(!plugin.getConfig().isSet("config.mysql.host")){
			plugin.getConfig().set("config.mysql.host", "localhost:3306");
		}
		if(!plugin.getConfig().isSet("config.mysql.database")){
			plugin.getConfig().set("config.mysql.database", "");
		}
		if(!plugin.getConfig().isSet("config.mysql.username")){
			plugin.getConfig().set("config.mysql.username", "root");
		}
		if(!plugin.getConfig().isSet("config.mysql.password")){
			plugin.getConfig().set("config.mysql.password", "");
		}
		if(!plugin.getConfig().isSet("config.mysql.form")){
			plugin.getConfig().set("config.mysql.form", "");
		}
		
		//保存配置
		plugin.saveConfig();
	}
	
	public static void setLanguageDefault(ConfigLoadLanguage languageConfig){
		//提示部分
		if(!languageConfig.getLanguageConfig().isSet("language.login.tip")){
			languageConfig.getLanguageConfig().set("language.login.tip", "§4请先使用/login <密码>进行登录。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.login.loginyet")){
			languageConfig.getLanguageConfig().set("language.login.loginyet", "§4你已经登录过了。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.login.loginsuccess")){
			languageConfig.getLanguageConfig().set("language.login.loginsuccess", "§2登录成功。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.login.joinboardcast")){
			languageConfig.getLanguageConfig().set("language.login.joinboardcast", "§6{username} 加入了游戏。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.login.autologin")){
			languageConfig.getLanguageConfig().set("language.login.autologin", "§2欢迎回来，已自动登录。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.login.logoutsuccess")){
			languageConfig.getLanguageConfig().set("language.login.logoutsuccess", "§2成功登出。");
		}
		
		//管理员部分
		if(!languageConfig.getLanguageConfig().isSet("language.admin.setloginlocationsuccess")){
			languageConfig.getLanguageConfig().set("language.admin.setloginlocationsuccess", "§2登录地点设置成功。");
		}
		
		//错误部分
		if(!languageConfig.getLanguageConfig().isSet("language.error.nologin")){
			languageConfig.getLanguageConfig().set("language.error.nologin", "§4你还没登录。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.error.mustplayer")){
			languageConfig.getLanguageConfig().set("language.error.mustplayer", "§4这个命令必须由玩家发送。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.error.argswrong")){
			languageConfig.getLanguageConfig().set("language.error.argswrong", "§4参数过多或者过少！");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.error.passwordwrong")){
			languageConfig.getLanguageConfig().set("language.error.passwordwrong", "§4密码错误。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.error.nouser")){
			languageConfig.getLanguageConfig().set("language.error.nouser", "§4未找到用户。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.error.dbselectwrong")){
			languageConfig.getLanguageConfig().set("language.error.dbselectwrong", "§4数据库查询失败，请重试。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.error.passwordencodewrong")){
			languageConfig.getLanguageConfig().set("language.error.passwordencodewrong", "§4密码加密失败，请重试。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.error.encodeensupport")){
			languageConfig.getLanguageConfig().set("language.error.encodeensupport", "§4密码加密失败。(不支持的加密)，请重试。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.error.dbconnecterror")){
			languageConfig.getLanguageConfig().set("language.error.dbconnecterror", "§4数据库连接失败，请重试。");
		}
		
		//后台部分
		if(!languageConfig.getLanguageConfig().isSet("language.console.enable")){
			languageConfig.getLanguageConfig().set("language.console.enable", "插件已加载。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.console.disable")){
			languageConfig.getLanguageConfig().set("language.console.disable", "插件已卸载.");
		}
		
		//保存配置
		languageConfig.saveLanguageConfig();
	}
}
