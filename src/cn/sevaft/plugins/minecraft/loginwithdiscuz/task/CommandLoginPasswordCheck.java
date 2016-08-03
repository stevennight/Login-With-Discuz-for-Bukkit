package cn.sevaft.plugins.minecraft.loginwithdiscuz.task;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import cn.sevaft.plugins.minecraft.loginwithdiscuz.function.Common;
import cn.sevaft.plugins.minecraft.loginwithdiscuz.main.Loginwithdiscuz;
import cn.sevaft.plugins.minecraft.loginwithdiscuz.mysql.Connect;

public class CommandLoginPasswordCheck extends BukkitRunnable {
	 
    private final Player sender;
    private final String passworduserinput;
    private final Plugin plugin;
 
    public CommandLoginPasswordCheck(Plugin plugin,Player sender,String passworduserinput) {
    	this.plugin = plugin;
        this.sender = sender;
        this.passworduserinput = passworduserinput;
    }
 
    @Override
    public void run() {
    	Connect connect = new Connect();
    	Connection con;
    	con=connect.getConnection(this.plugin);
    	if(con != null){
    		String formpre = plugin.getConfig().getString("config.mysql.formpre");
    		String query = "SELECT uid,password,salt FROM `"+formpre+"ucenter_members` WHERE username=?";
    		try {
    			PreparedStatement sql=con.prepareStatement(query);
        		sql.setString(1, sender.getName());
        		ResultSet result=sql.executeQuery();
				if(result.next()){
					int uid = result.getInt(1);
					String passwordindb=result.getString(2);
					String salt = result.getString(3);
					
					//����������㡣
					MessageDigest md5 =MessageDigest.getInstance("MD5");
					String password=byteArrayToHex(md5.digest(this.passworduserinput.getBytes("utf-8"))).toLowerCase();//һ��Ҫtolowercase
					String passwordaddsalt=password+salt;
					String passwordthelast=byteArrayToHex(md5.digest(passwordaddsalt.getBytes("utf-8"))).toLowerCase();
					
					//�Ƚ�����
					if(passwordthelast.equals(passwordindb)){
			    		
						//��ȡ�ֻ�������Ƿ񼤻�
						boolean mustemail = plugin.getConfig().getBoolean("config.account.mustemail");
						boolean isemail = true;
						boolean mustmobile = plugin.getConfig().getBoolean("config.account.mustmobile");
						boolean ismobile = true;
						if(mustemail){
							//��ȡ�Ƿ񼤻�����
				    		String query2 = "SELECT emailstatus FROM `"+formpre+"common_member` WHERE uid=?";
				    		PreparedStatement sql2=con.prepareStatement(query2);
			        		sql2.setInt(1, uid);
			        		ResultSet result2=sql2.executeQuery();
							if(result2.next()){
								isemail = result2.getBoolean(1);
							}else{
								isemail = false;
							}
						}
						if(mustmobile){
							//��ȡ�Ƿ񼤻�����
				    		String query2 = "SELECT status FROM `"+formpre+"mobilebind_user` WHERE uid=?";
				    		PreparedStatement sql2=con.prepareStatement(query2);
			        		sql2.setInt(1, uid);
			        		ResultSet result2=sql2.executeQuery();
							if(result2.next()){
								ismobile = true;
							}else{
								ismobile = false;
							}
						}
						
						if(isemail && ismobile){
							
							//��¼�ɹ�
							sender.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.login.loginsuccess"));
							String bcm_join=Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.login.joinboardcast");
							bcm_join=bcm_join.replace("{username}", sender.getName());
							plugin.getServer().broadcastMessage(bcm_join);
							Loginwithdiscuz.userkicktask.get(sender.getName()).cancel();
					    	Loginwithdiscuz.userkicktask.remove(sender.getName());
					    	Loginwithdiscuz.usertipstask.get(sender.getName()).cancel();
					    	Loginwithdiscuz.usertipstask.remove(sender.getName());
					    	
					    	//����������һ��λ�á���
					    	boolean openloginpoint = this.plugin.getConfig().getBoolean("config.loginpoint.open");
					    	if(openloginpoint){
					    		sender.teleport(Common.getPlayerLastpos(this.plugin, sender));
					    	}
					    	
					    	//������ҵ�¼״̬��
					    	Loginwithdiscuz.loginuser.put(sender.getName(), true);
							
						}else{
							
							String mustquest = Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.login.mustquest");
							String mustemailtip = Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.login.mustemail");
							String mustemobiletip = Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.login.mustmobile");
							String changeword = "";
							if(!isemail){
								changeword += mustemailtip;
							}
							if(!ismobile){
								changeword += mustemobiletip;
							}
							mustquest = mustquest.replace("{quests}",changeword);
							sender.sendMessage(mustquest);
						}
						
					}else{
						sender.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.error.passwordwrong"));
					}
					
					//�ر����ݿ�����
					con.close();
				}else{
					sender.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.error.nouser"));
				}
			} catch (SQLException e) {
				sender.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.error.dbselectwrong"));
			} catch (NoSuchAlgorithmException e) {
				sender.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.error.passwordencodewrong"));
			} catch (UnsupportedEncodingException e) {
				sender.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.error.encodeensupport"));
			}
    	}else{
    		sender.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.error.dbconnecterror"));
    	}
    	this.cancel();
    }
    
  //����������
    public static String byteArrayToHex(byte[] byteArray) {  
        
        // ���ȳ�ʼ��һ���ַ����飬�������ÿ��16�����ַ�  
        char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };  
        // newһ���ַ����飬�������������ɽ���ַ����ģ�����һ�£�һ��byte�ǰ�λ�����ƣ�Ҳ����2λʮ�������ַ���2��8�η�����16��2�η�����  
        char[] resultCharArray =new char[byteArray.length * 2];  
        // �����ֽ����飬ͨ��λ���㣨λ����Ч�ʸߣ���ת�����ַ��ŵ��ַ�������ȥ  
        int index = 0; 
        for (byte b : byteArray) {  
           resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];  
           resultCharArray[index++] = hexDigits[b& 0xf];  
        }
        // �ַ�������ϳ��ַ�������  
        return new String(resultCharArray);  
    }
}
