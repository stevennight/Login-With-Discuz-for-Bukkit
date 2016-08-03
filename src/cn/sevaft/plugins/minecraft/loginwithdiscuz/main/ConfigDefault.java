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
		String world=null;
			Iterator<World> worlds = plugin.getServer().getWorlds().iterator();
			
			if(worlds.hasNext()){
				world = worlds.next().getName();
			}
		if(!plugin.getConfig().isSet("config.loginpoint.defaultworld")){
			plugin.getConfig().set("config.loginpoint.defaultworld",world);
		}
		if(!plugin.getConfig().isSet("config.loginpoint."+world+".posx")){
			plugin.getConfig().set("config.loginpoint."+world+".posx", 0.0);
		}
		if(!plugin.getConfig().isSet("config.loginpoint."+world+".posy")){
			plugin.getConfig().set("config.loginpoint."+world+".posy", 80.0);
		}
		if(!plugin.getConfig().isSet("config.loginpoint."+world+".posz")){
			plugin.getConfig().set("config.loginpoint."+world+".posz", 0.0);
		}
		if(!plugin.getConfig().isSet("config.loginpoint."+world+".posyaw")){
			plugin.getConfig().set("config.loginpoint."+world+".posyaw", 0.0f);
		}
		if(!plugin.getConfig().isSet("config.loginpoint."+world+".pospitch")){
			plugin.getConfig().set("config.loginpoint."+world+".pospitch", 0.0f);
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
		if(!plugin.getConfig().isSet("config.mysql.formpre")){
			plugin.getConfig().set("config.mysql.formpre", "pre_");
		}
		//账号要求(是否要绑定手机和邮箱)
		if(!plugin.getConfig().isSet("config.account.mustemail")){
			plugin.getConfig().set("config.account.mustemail", true);
		}
		if(!plugin.getConfig().isSet("config.account.mustmobile")){
			plugin.getConfig().set("config.account.mustmobile", false);
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
		if(!languageConfig.getLanguageConfig().isSet("language.login.logoutboardcast")){
			languageConfig.getLanguageConfig().set("language.login.logoutboardcast", "§6{username} 离开了游戏。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.login.autologin")){
			languageConfig.getLanguageConfig().set("language.login.autologin", "§2欢迎回来，已自动登录。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.login.logoutsuccess")){
			languageConfig.getLanguageConfig().set("language.login.logoutsuccess", "§2成功登出。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.login.mustquest")){//必须绑定手机邮箱提示。
			languageConfig.getLanguageConfig().set("language.login.mustquest", "§4您的账号必须绑定并激活§6{quests}§4才能登录游戏。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.login.mustemail")){//必须绑定手机邮箱提示。
			languageConfig.getLanguageConfig().set("language.login.mustemail", "【邮箱】");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.login.mustmobile")){//必须绑定手机邮箱提示。
			languageConfig.getLanguageConfig().set("language.login.mustmobile", "【手机】");
		}
		
		//管理员部分
		if(!languageConfig.getLanguageConfig().isSet("language.admin.setloginlocationsuccess")){
			languageConfig.getLanguageConfig().set("language.admin.setloginlocationsuccess", "§2世界——{world}的登录地点设置成功。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.admin.reloadsuccess")){
			languageConfig.getLanguageConfig().set("language.admin.reloadsuccess", "§2配置已重载。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.admin.saveconfigsuccess")){
			languageConfig.getLanguageConfig().set("language.admin.saveconfigsuccess", "§2配置已保存.");
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
			languageConfig.getLanguageConfig().set("language.console.enable", "§2插件已加载。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.console.disable")){
			languageConfig.getLanguageConfig().set("language.console.disable", "§2插件已卸载.");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.console.reloadsuccess")){
			languageConfig.getLanguageConfig().set("language.console.reloadsuccess", "§2配置已重载。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.console.saveconfigsuccess")){
			languageConfig.getLanguageConfig().set("language.console.saveconfigsuccess", "§2配置已保存.");
		}
		
		//插件信息
		if(!languageConfig.getLanguageConfig().isSet("language.help.console")){
			languageConfig.getLanguageConfig().set("language.help.console", ""
					+ "login with discuz插件帮助：{newline}"
					+ "控制台不能使用玩家的指令，如/login 和 /logout。{newline}"
					+ "/lwd [类型]能对插件进行一定的管理。{newline}"
					+ "类型为help，获取帮助信息。{newline}"
					+ "类型为reload，重载配置、语言、用户存档。{newline}"
					+ "类型为saveconfig，立刻保存所有配置(同上)。{newline}"
					+ "类型为kickautologin，清除玩家自动登录信息，下次玩家登录需要使用密码登录。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.help.admin")){
			languageConfig.getLanguageConfig().set("language.help.admin", ""
					+ "login with discuz插件帮助：{newline}"
					+ "/login [密码] 登录{newline}"
					+ "/logout 登出{newline}"
					+ "/lwd [类型]能对插件进行一定的管理。{newline}"
					+ "类型为help，获取帮助信息。{newline}"
					+ "类型为reload，重载配置、语言、用户存档。{newline}"
					+ "类型为saveconfig，立刻保存所有配置(同上)。{newline}"
					+ "类型为kickautologin，清除玩家自动登录信息，下次玩家登录需要使用密码登录。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.help.player")){
			languageConfig.getLanguageConfig().set("language.help.player", ""
					+ "login with discuz插件帮助：{newline}"
					+ "/login [密码] 登录{newline}"
					+ "/logout 登出{newline}"
					+ "/lwd help 获取帮助信息。");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.help.kickautologin.playernotfound")){
			languageConfig.getLanguageConfig().set("language.help.kickautologin.playernotfound", "玩家不存在！");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.help.kickautologin.success")){
			languageConfig.getLanguageConfig().set("language.help.kickautologin.success", "已重置玩家{username}的自动登录。如果玩家不在线，下次玩家登录必须重新进行密码登录，否则无效。");
		}
		
		//保存配置
		languageConfig.saveLanguageConfig();
	}
}
