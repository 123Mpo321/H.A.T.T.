package com.hackyguys.hackallthethings.commands;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.hackyguys.hackallthethings.Base;
import com.hackyguys.hackallthethings.filesystem.Directory;
import com.hackyguys.hackallthethings.filesystem.FSNode;

public class Commands{
	
	public static HashMap<String, Command> standardCommandMap = new HashMap<>();
	
	static {
		
		standardCommandMap.put("date", s -> {
			System.out.println((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(new Date()));
		});
		
		standardCommandMap.put("ls", s -> {
			for(FSNode f: Base.currentDir.getChildren())
				System.out.println(f.getName());
		});
		
		standardCommandMap.put("cd", s -> {
			// TODO
		});
		
		standardCommandMap.put("mv", s -> {
			// TODO
		});
		
		standardCommandMap.put("whoami", s -> {
			System.out.println(System.getProperty("user.name"));
		});
		
		standardCommandMap.put("rm", s -> {
			if(!Base.currentDir.deleteFile(s)) System.out.println("File does not exist.");
		});
		
		standardCommandMap.put("cp", s -> {
			// TODO
			Directory cd = Base.currentDir;
			if(s.startsWith("-r")){
				
			}
			if(s.split(" ").length == 1){
				if(!cd.contains(s)){
					System.out.println("File does not exist.");
					return;
				}else{
					
				}
			}else if(s.split(" ").length == 2){
				
			}else System.out.println("Invalid syntax. e.g. cp <filename> [destination]");
		});
		
	}
	
}
