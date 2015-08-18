package com.hackyguys.hackallthethings.terminal.os.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


public class User{
	
	private MessageDigest MD5;
	private String username;
	public final UUID ID;
	private String password;
	
	public User(String name, String password){
		this.username = name;
		ID = UUID.randomUUID();
		try{
			MD5 = MessageDigest.getInstance("MD5");
			this.password = new String(MD5.digest(password.getBytes()));
		}catch(NoSuchAlgorithmException e){
			this.password = "5f4dcc3b5aa765d61d8327deb882cf99"; // "password"
		}
	}
	
	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		if(MD5 != null)
			this.password = new String(MD5.digest(password.getBytes()));
	}

	public UUID getID(){
		return ID;
	}
	
}
