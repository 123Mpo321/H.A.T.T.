package com.hackyguys.hackallthethings.commands;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.hackyguys.hackallthethings.Base;
import com.hackyguys.hackallthethings.filesystem.Directory;
import com.hackyguys.hackallthethings.filesystem.File;
import com.hackyguys.hackallthethings.filesystem.FileTypes;

public class Commands{
	
	public static HashMap<String, Command> standardCommandMap = new HashMap<>();
	
	static {
		
		standardCommandMap.put("date", s -> {
			System.out.println((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(new Date()));
		});
		
		standardCommandMap.put("ls", s -> {
			for(File f: Base.currentDir.getChildren())
				System.out.println(f.getName() + "\t" + f.getType());
		});
		
		standardCommandMap.put("cd", s -> {
			if((s.equals("..") || s.equals("../")) && Base.currentDir.getParent() != null) Base.currentDir = Base.currentDir.getParent();
			else if(Base.currentDir.contains(s) && Base.currentDir.getFile(s) instanceof Directory) Base.currentDir = (Directory) Base.currentDir.getFile(s);
			else System.out.println("Invalid directory name.");
		});
		
		standardCommandMap.put("mv", s -> {
			if(s.split(" ").length != 2){
				System.out.println("Invalid Systax. e.g. mv <filename> <destination>");
				return;
			}
			String f = s.split(" ")[0];
			String target = s.split(" ")[1];
			if(Base.currentDir.contains(f)){
				File file = Base.currentDir.getFile(f);
				if(target == ".." || target == "" && file.getParent().getParent() != null){
					Directory td = file.getParent().getParent();
					td.addFile(file);
					Base.currentDir.deleteFile(file);
				}else if(file.getParent().contains(target)){
					Directory td = (Directory) file.getParent().getFile(target);
					td.addFile(file);
					Base.currentDir.deleteFile(file);
				}else System.out.println("Invalid directory.");
			}else System.out.println("Invalid file name.");
		});
		
		standardCommandMap.put("whoami", s -> {
			System.out.println(System.getProperty("user.name"));
		});
		
		standardCommandMap.put("rm", s -> {
			if(!Base.currentDir.deleteFile(s)) System.out.println("File does not exist.");
		});
		
		standardCommandMap.put("cp", s -> {
			Directory cd = Base.currentDir;
			if(s.startsWith("-r")){
				if(s.split(" ").length != 3){System.out.println("Invald sysnax. e.g. cp -r <dirname1> <dirname2>"); return;}
				String d1 = s.split(" ")[1];
				String d2 = s.split(" ")[2];
				if(!cd.contains(d1) || (cd.contains(d1) && !(cd.getFile(d1) instanceof Directory))){System.out.println("The directory '"+d1+"' does not exist.");}
				if(cd.contains(d2, FileTypes.DIRECTORY)){
					Directory dir1 = (Directory) cd.getFile(d1);
					Directory dir2 = (Directory) cd.getFile(d2);
					dir2.addFiles(dir1.getChildren());
				}else{
					Directory dir1 = (Directory) cd.getFile(d1);
					Directory dir2 = new Directory(dir1.getName(), dir1.getParent());
					dir2.addFiles(dir1.getChildren());
				}
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
