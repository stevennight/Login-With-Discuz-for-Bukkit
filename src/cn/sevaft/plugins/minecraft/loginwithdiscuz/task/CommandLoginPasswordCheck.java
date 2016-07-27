package cn.sevaft.plugins.minecraft.loginwithdiscuz.task;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

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
    		String form = plugin.getConfig().getString("config.mysql.form");
    		String query = "SELECT password,salt FROM `"+form+"` WHERE username=?";
    		try {
    			PreparedStatement sql=con.prepareStatement(query);
        		sql.setString(1, sender.getName());
        		ResultSet result=sql.executeQuery();
				if(result.next()){
					String passwordindb=result.getString(1);
					String salt = result.getString(2);
					
					//����������㡣
					MessageDigest md5 =MessageDigest.getInstance("MD5");
					String password=byteArrayToHex(md5.digest(this.passworduserinput.getBytes("utf-8"))).toLowerCase();
					String passwordaddsalt=password+salt;
					String passwordthelast=byteArrayToHex(md5.digest(passwordaddsalt.getBytes("utf-8"))).toLowerCase();
					
					//�Ƚ�����
					if(passwordthelast.equals(passwordindb)){
						Loginwithdiscuz.loginuser.put(sender.getName(), true);
						sender.sendMessage(Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.login.loginsuccess"));
						String bcm_join=Loginwithdiscuz.languageConfig.getLanguageConfig().getString("language.login.joinboardcast");
						bcm_join=bcm_join.replace("{username}", sender.getName());
						plugin.getServer().broadcastMessage(bcm_join);
						Loginwithdiscuz.userkicktask.get(sender.getName()).cancel();
				    	Loginwithdiscuz.userkicktask.remove(sender.getName());
				    	Loginwithdiscuz.usertipstask.get(sender.getName()).cancel();
				    	Loginwithdiscuz.usertipstask.remove(sender.getName());
				    	
				    	//�̶���¼λ�á�
				    	boolean openloginpoint = this.plugin.getConfig().getBoolean("config.loginpoint.open");
				    	if(openloginpoint){
				    		boolean issetconfig=Loginwithdiscuz.userConfig.getUserConfig().isSet("Users."+sender.getName()+".posx")&&
				    				Loginwithdiscuz.userConfig.getUserConfig().isSet("Users."+sender.getName()+".posy")&&
				    				Loginwithdiscuz.userConfig.getUserConfig().isSet("Users."+sender.getName()+".posz")&&
				    				Loginwithdiscuz.userConfig.getUserConfig().isSet("Users."+sender.getName()+".posyaw")&&
				    				Loginwithdiscuz.userConfig.getUserConfig().isSet("Users."+sender.getName()+".pospitch");
				    		if(issetconfig){
				    			World world = this.plugin.getServer().getWorld(
				    					Loginwithdiscuz.userConfig.getUserConfig().getString("Users."+sender.getName()+".world"));
					    		double posx = Loginwithdiscuz.userConfig.getUserConfig().getDouble("Users."+sender.getName()+".posx");
					    		double posy = Loginwithdiscuz.userConfig.getUserConfig().getDouble("Users."+sender.getName()+".posy");
					    		double posz = Loginwithdiscuz.userConfig.getUserConfig().getDouble("Users."+sender.getName()+".posz");
					    		float posyaw = (float) Loginwithdiscuz.userConfig.getUserConfig().getDouble("Users."+sender.getName()+".posyaw");
					    		float pospitch = (float) Loginwithdiscuz.userConfig.getUserConfig().getDouble("Users."+sender.getName()+".pospitch");
					    		Location loc = new Location(world,posx,posy,posz,posyaw,pospitch);
					    		sender.teleport(loc);
				    		}else{
				    			//û��ѡ��ֱ�Ӵ��͵���������㡣
				    			sender.teleport(sender.getWorld().getSpawnLocation());
				    		}
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
