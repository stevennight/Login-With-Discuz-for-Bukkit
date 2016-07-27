package cn.sevaft.plugins.minecraft.loginwithdiscuz.mysql;

import java.sql.*;

import org.bukkit.plugin.Plugin;

public class Connect {
	
	Connection con;
	
	public Connection getConnection(Plugin plugin){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			return null;
		}
		
		//Á¬½Ó
		String hostname=plugin.getConfig().getString("config.mysql.host");
		String database=plugin.getConfig().getString("config.mysql.database");
		String username=plugin.getConfig().getString("config.mysql.username");
		String password=plugin.getConfig().getString("config.mysql.password");
		try{
			con = DriverManager.getConnection("jdbc:mysql://"+hostname+"/"+database,username,password);
		}catch (Exception e) {  
			return null;
        }
		return con;
	}
}
