package com.hackyguys.hackallthethings.terminal.os;

import java.util.HashMap;

import com.hackyguys.hackallthethings.filesystem.Directory;
import com.hackyguys.hackallthethings.terminal.FileTree;
import com.hackyguys.hackallthethings.terminal.os.user.User;


public abstract class OperatingSystem{
	
	public final String NAME;
	private FileTree fileTree;
	private HashMap<String, String> users;
	
	public OperatingSystem(String name, FileTree fileTree){
		NAME = name;
		this.fileTree = fileTree;
		users = new HashMap<>();
	}
	
	public User addUser(String username, String password){
		User user = new User(username, password);
		users.put(user.getUsername(), user.getPassword());
		return user;
	}
	
	public Directory getRootDirectory(){
		return fileTree.ROOT;
	}
	
}
