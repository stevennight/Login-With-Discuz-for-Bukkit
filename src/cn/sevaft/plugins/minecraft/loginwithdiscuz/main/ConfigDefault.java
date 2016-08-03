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
		if(!plugin.getConfig().isSet("config.mysql.formpre")){
			plugin.getConfig().set("config.mysql.formpre", "pre_");
		}
		//�˺�Ҫ��(�Ƿ�Ҫ���ֻ�������)
		if(!plugin.getConfig().isSet("config.account.mustemail")){
			plugin.getConfig().set("config.account.mustemail", true);
		}
		if(!plugin.getConfig().isSet("config.account.mustmobile")){
			plugin.getConfig().set("config.account.mustmobile", false);
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
		if(!languageConfig.getLanguageConfig().isSet("language.login.logoutboardcast")){
			languageConfig.getLanguageConfig().set("language.login.logoutboardcast", "��6{username} �뿪����Ϸ��");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.login.autologin")){
			languageConfig.getLanguageConfig().set("language.login.autologin", "��2��ӭ���������Զ���¼��");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.login.logoutsuccess")){
			languageConfig.getLanguageConfig().set("language.login.logoutsuccess", "��2�ɹ��ǳ���");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.login.mustquest")){//������ֻ�������ʾ��
			languageConfig.getLanguageConfig().set("language.login.mustquest", "��4�����˺ű���󶨲������6{quests}��4���ܵ�¼��Ϸ��");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.login.mustemail")){//������ֻ�������ʾ��
			languageConfig.getLanguageConfig().set("language.login.mustemail", "�����䡿");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.login.mustmobile")){//������ֻ�������ʾ��
			languageConfig.getLanguageConfig().set("language.login.mustmobile", "���ֻ���");
		}
		
		//����Ա����
		if(!languageConfig.getLanguageConfig().isSet("language.admin.setloginlocationsuccess")){
			languageConfig.getLanguageConfig().set("language.admin.setloginlocationsuccess", "��2���硪��{world}�ĵ�¼�ص����óɹ���");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.admin.reloadsuccess")){
			languageConfig.getLanguageConfig().set("language.admin.reloadsuccess", "��2���������ء�");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.admin.saveconfigsuccess")){
			languageConfig.getLanguageConfig().set("language.admin.saveconfigsuccess", "��2�����ѱ���.");
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
			languageConfig.getLanguageConfig().set("language.console.enable", "��2����Ѽ��ء�");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.console.disable")){
			languageConfig.getLanguageConfig().set("language.console.disable", "��2�����ж��.");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.console.reloadsuccess")){
			languageConfig.getLanguageConfig().set("language.console.reloadsuccess", "��2���������ء�");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.console.saveconfigsuccess")){
			languageConfig.getLanguageConfig().set("language.console.saveconfigsuccess", "��2�����ѱ���.");
		}
		
		//�����Ϣ
		if(!languageConfig.getLanguageConfig().isSet("language.help.console")){
			languageConfig.getLanguageConfig().set("language.help.console", ""
					+ "login with discuz���������{newline}"
					+ "����̨����ʹ����ҵ�ָ���/login �� /logout��{newline}"
					+ "/lwd [����]�ܶԲ������һ���Ĺ���{newline}"
					+ "����Ϊhelp����ȡ������Ϣ��{newline}"
					+ "����Ϊreload���������á����ԡ��û��浵��{newline}"
					+ "����Ϊsaveconfig�����̱�����������(ͬ��)��{newline}"
					+ "����Ϊkickautologin���������Զ���¼��Ϣ���´���ҵ�¼��Ҫʹ�������¼��");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.help.admin")){
			languageConfig.getLanguageConfig().set("language.help.admin", ""
					+ "login with discuz���������{newline}"
					+ "/login [����] ��¼{newline}"
					+ "/logout �ǳ�{newline}"
					+ "/lwd [����]�ܶԲ������һ���Ĺ���{newline}"
					+ "����Ϊhelp����ȡ������Ϣ��{newline}"
					+ "����Ϊreload���������á����ԡ��û��浵��{newline}"
					+ "����Ϊsaveconfig�����̱�����������(ͬ��)��{newline}"
					+ "����Ϊkickautologin���������Զ���¼��Ϣ���´���ҵ�¼��Ҫʹ�������¼��");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.help.player")){
			languageConfig.getLanguageConfig().set("language.help.player", ""
					+ "login with discuz���������{newline}"
					+ "/login [����] ��¼{newline}"
					+ "/logout �ǳ�{newline}"
					+ "/lwd help ��ȡ������Ϣ��");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.help.kickautologin.playernotfound")){
			languageConfig.getLanguageConfig().set("language.help.kickautologin.playernotfound", "��Ҳ����ڣ�");
		}
		if(!languageConfig.getLanguageConfig().isSet("language.help.kickautologin.success")){
			languageConfig.getLanguageConfig().set("language.help.kickautologin.success", "���������{username}���Զ���¼�������Ҳ����ߣ��´���ҵ�¼�������½��������¼��������Ч��");
		}
		
		//��������
		languageConfig.saveLanguageConfig();
	}
}
