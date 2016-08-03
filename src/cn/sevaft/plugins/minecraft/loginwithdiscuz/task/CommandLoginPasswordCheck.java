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
					
					//密码加密运算。
					MessageDigest md5 =MessageDigest.getInstance("MD5");
					String password=byteArrayToHex(md5.digest(this.passworduserinput.getBytes("utf-8"))).toLowerCase();//一定要tolowercase
					String passwordaddsalt=password+salt;
					String passwordthelast=byteArrayToHex(md5.digest(passwordaddsalt.getBytes("utf-8"))).toLowerCase();
					
					//比较密码
					if(passwordthelast.equals(passwordindb)){
			    		
						//获取手机邮箱的是否激活
						boolean mustemail = plugin.getConfig().getBoolean("config.account.mustemail");
						boolean isemail = true;
						boolean mustmobile = plugin.getConfig().getBoolean("config.account.mustmobile");
						boolean ismobile = true;
						if(mustemail){
							//获取是否激活邮箱
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
							//获取是否激活邮箱
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
							
							//登录成功
							sender.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.login.loginsuccess"));
							String bcm_join=Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.login.joinboardcast");
							bcm_join=bcm_join.replace("{username}", sender.getName());
							plugin.getServer().broadcastMessage(bcm_join);
							Loginwithdiscuz.userkicktask.get(sender.getName()).cancel();
					    	Loginwithdiscuz.userkicktask.remove(sender.getName());
					    	Loginwithdiscuz.usertipstask.get(sender.getName()).cancel();
					    	Loginwithdiscuz.usertipstask.remove(sender.getName());
					    	
					    	//返回玩家最后一次位置。。
					    	boolean openloginpoint = this.plugin.getConfig().getBoolean("config.loginpoint.open");
					    	if(openloginpoint){
					    		sender.teleport(Common.getPlayerLastpos(this.plugin, sender));
					    	}
					    	
					    	//更新玩家登录状态。
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
					
					//关闭数据库连接
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
    
  //加密密码用
    public static String byteArrayToHex(byte[] byteArray) {  
        
        // 首先初始化一个字符数组，用来存放每个16进制字符  
        char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };  
        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））  
        char[] resultCharArray =new char[byteArray.length * 2];  
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去  
        int index = 0; 
        for (byte b : byteArray) {  
           resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];  
           resultCharArray[index++] = hexDigits[b& 0xf];  
        }
        // 字符数组组合成字符串返回  
        return new String(resultCharArray);  
    }
}
