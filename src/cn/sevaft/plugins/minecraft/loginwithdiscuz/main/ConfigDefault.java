package cn.sevaft.plugins.minecraft.loginwithdiscuz.main;

import java.util.Iterator;

import org.bukkit.World;
import org.bukkit.plugin.Plugin;

public class ConfigDefault {
	
	public static void SetConfigDefault(Plugin plugin){
		//�����������
		//��¼��ʱʱ��
		if(!plugin.getConfig().isSet("config.overtime")){
			plugin.getConfig().set("config.overtime", 30);
		}
		//��ʾ��¼���ʱ��
		if(!plugin.getConfig().isSet("config.tipstime")){
			plugin.getConfig().set("config.tipstime", 5);
		}
		//���¼
		if(!plugin.getConfig().isSet("config.autologin.overtime")){
			plugin.getConfig().set("config.autologin.overtime", 300);
		}
		//��¼�ص�
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
		
		//���ݿ�����
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
		
		//��������
		plugin.saveConfig();
	}
	
	public static void setLanguageDefault(ConfigLoadLanguage languageConfig){
		//��ʾ����
		if(!languageConfig.getLanguageConfig().isSet("language.login.tip")){
			languageConfig.getLanguageConfig().set("language.login.tip", "��4����ʹ��/login <����>���е�¼��");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.login.loginyet")){
			languageConfig.getLanguageConfig().set("language.login.loginyet", "��4���Ѿ���¼���ˡ�");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.login.loginsuccess")){
			languageConfig.getLanguageConfig().set("language.login.loginsuccess", "��2��¼�ɹ���");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.login.joinboardcast")){
			languageConfig.getLanguageConfig().set("language.login.joinboardcast", "��6{username} ��������Ϸ��");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.login.autologin")){
			languageConfig.getLanguageConfig().set("language.login.autologin", "��2��ӭ���������Զ���¼��");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.login.logoutsuccess")){
			languageConfig.getLanguageConfig().set("language.login.logoutsuccess", "��2�ɹ��ǳ���");
		}
		
		//����Ա����
		if(!languageConfig.getLanguageConfig().isSet("language.admin.setloginlocationsuccess")){
			languageConfig.getLanguageConfig().set("language.admin.setloginlocationsuccess", "��2��¼�ص����óɹ���");
		}
		
		//���󲿷�
		if(!languageConfig.getLanguageConfig().isSet("language.error.nologin")){
			languageConfig.getLanguageConfig().set("language.error.nologin", "��4�㻹û��¼��");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.error.mustplayer")){
			languageConfig.getLanguageConfig().set("language.error.mustplayer", "��4��������������ҷ��͡�");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.error.argswrong")){
			languageConfig.getLanguageConfig().set("language.error.argswrong", "��4����������߹��٣�");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.error.passwordwrong")){
			languageConfig.getLanguageConfig().set("language.error.passwordwrong", "��4�������");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.error.nouser")){
			languageConfig.getLanguageConfig().set("language.error.nouser", "��4δ�ҵ��û���");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.error.dbselectwrong")){
			languageConfig.getLanguageConfig().set("language.error.dbselectwrong", "��4���ݿ��ѯʧ�ܣ������ԡ�");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.error.passwordencodewrong")){
			languageConfig.getLanguageConfig().set("language.error.passwordencodewrong", "��4�������ʧ�ܣ������ԡ�");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.error.encodeensupport")){
			languageConfig.getLanguageConfig().set("language.error.encodeensupport", "��4�������ʧ�ܡ�(��֧�ֵļ���)�������ԡ�");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.error.dbconnecterror")){
			languageConfig.getLanguageConfig().set("language.error.dbconnecterror", "��4���ݿ�����ʧ�ܣ������ԡ�");
		}
		
		//��̨����
		if(!languageConfig.getLanguageConfig().isSet("language.console.enable")){
			languageConfig.getLanguageConfig().set("language.console.enable", "����Ѽ��ء�");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.console.disable")){
			languageConfig.getLanguageConfig().set("language.console.disable", "�����ж��.");
		}
		
		//��������
		languageConfig.saveLanguageConfig();
	}
}
