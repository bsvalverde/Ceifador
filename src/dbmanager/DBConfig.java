package dbmanager;

public class DBConfig {
	
	protected static String host = "localhost";
	protected static String port = "1984";
	protected static String user = "admin";
	protected static String password = "admin";
	protected static String dbName = "TCC";
	
	public DBConfig(){	}
	
	public static String getHost(){
		return host;
	}
	
	public static String getPort(){
		return port;
	}
	
	public static String getUser(){
		return user;
	}
	
	public static String getPassword(){
		return password;
	}
	
	public static String getDBName(){
		return dbName;
	}
	
	public static void setHost(String newHost){
		host = newHost;
	}
	
	public static void setPort(String newPort){
		port = newPort;
	}
	
	public static void setUser(String newUser){
		user = newUser;
	}
	
	public static void setPassword(String newPassword){
		password = newPassword;
	}
	
	public static void setDbName(String newDbName){
		dbName = newDbName;
	}
	
}