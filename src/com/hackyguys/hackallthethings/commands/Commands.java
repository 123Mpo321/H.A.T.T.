package com.hackyguys.hackallthethings.commands;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.hackyguys.hackallthethings.Base;
import com.hackyguys.hackallthethings.filesystem.BasicFile;
import com.hackyguys.hackallthethings.filesystem.Directory;
import com.hackyguys.hackallthethings.filesystem.File;

public class Commands{
	
	public static HashMap<String, Method> map = new HashMap<>();
	
	{
		try{
			map.put("date", getClass().getMethod("date", String.class));
			map.put("ls", getClass().getMethod("ls", String.class));
			map.put("mv", getClass().getMethod("mv", String.class));
			map.put("cd", getClass().getMethod("cd", String.class));
			map.put("whoami", getClass().getMethod("whoami", String.class));
			map.put("rm", getClass().getMethod("rm", String.class));
			map.put("cpr", getClass().getMethod("cpr", String.class));
			map.put("cp", getClass().getMethod("cp", String.class));
			map.put("view", getClass().getMethod("view", String.class));
		}catch(NoSuchMethodException | SecurityException e){
			e.printStackTrace();
		}
	}
	
	public static void date(String s){
		System.out.println((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(new Date()));
	}
	
	public static void ls(String s){
		for(File f: Base.currentDir.getChildren())
			System.out.println(f.getName());
	}
	
	//
	//
	//
	
	public static void cd(String s){
		if((s.equals("..") || s.equals("../")) && Base.currentDir.getParent() != null) Base.currentDir = Base.currentDir.getParent();
		else if(Base.currentDir.isInDirectory(s) && Base.currentDir.getFile(s) instanceof Directory) Base.currentDir = (Directory) Base.currentDir.getFile(s);
		else System.out.println("Invalid directory name.");
	}
	
	public static void mv(String s){
		if(s.split(" ").length != 2){
			System.out.println("Invalid Systax. e.g. mv <filename> <destination>");
			return;
		}
		String f = s.split(" ")[0];
		String target = s.split(" ")[1];
		if(Base.currentDir.isInDirectory(f)){
			File file = Base.currentDir.getFile(f);
			if(target == ".." || target == "" && file.getParent().getParent() != null){
				Directory td = file.getParent().getParent();
				td.getChildren().add(file);
				file.getParent().getChildren().remove(file);
				file.setParent(td);
				
			}else if(file.getParent().isInDirectory(target)){
				Directory td = (Directory) file.getParent().getFile(target);
				td.getChildren().add(file);
				file.getParent().getChildren().remove(file);
				file.setParent(td);
			}else System.out.println("Invalid directory.");
		}else System.out.println("Invalid file name.");
	}
	
	public static void whoami(String s){
		System.out.println(System.getProperty("user.name"));
	}
	
	public static void rm(String s){
		if(Base.currentDir.isInDirectory(s)) Base.currentDir.getChildren().remove(Base.currentDir.getFile(s));
		else System.out.println("File does not exist.");
	}
	
	public static void cpr(String s){
		Directory cd = Base.currentDir;
		if(s.split(" ").length == 1){
			if(cd.isInDirectory(s) && cd.getFile(s) instanceof Directory){
				boolean made = false;
				for(int i = 1; made == false; i++)
					if(!cd.isInDirectory(s + " (" + i + ")")){
						Directory d = new Directory(s + " (" + i + ")", cd.getFile(s).getParent());
						cd.getFile(s).getParent().addFile(d);
						made = true;
					}
			}
		}else if(s.split(" ").length == 2){
			if(cd.isInDirectory(s.split(" ")[0]) && cd.getFile(s.split(" ")[0]) instanceof Directory){
				if(cd.isInDirectory(s.split(" ")[1]) && cd.getFile(s.split(" ")[1]) instanceof Directory){
					boolean made = false;
					if(!((Directory) cd.getFile(s.split(" ")[1])).isInDirectory(s.split(" ")[0])){
						Directory d = new Directory(s.split(" ")[0], (Directory) cd.getFile(s.split(" ")[1]));
						((Directory) cd.getFile(s.split(" ")[1])).addFile(d);
						made = true;
					}
					for(int i = 1; made == false; i++)
						if(!((Directory) cd.getFile(s.split(" ")[1])).isInDirectory(s.split(" ")[0] + " (" + i + ")")){
							Directory d = new Directory(s.split(" ")[0] + " (" + i + ")", (Directory) cd.getFile(s.split(" ")[1]));
							((Directory) cd.getFile(s.split(" ")[1])).addFile(d);
							made = true;
						}
				}
			}
		}else System.out.println("Invalid syntax. e.g. cpr <directoryname> [destination]");
	}
	
	public static void cp(String s){
		Directory cd = Base.currentDir;
		if(s.split(" ").length == 1){
			if(cd.isInDirectory(s) && cd.getFile(s) instanceof File){
				boolean made = false;
				for(int i = 1; made == false; i++)
					if(!cd.isInDirectory(s + " (" + i + ")")){
						File d = new File(s + " (" + i + ")", cd.getFile(s).getParent());
						cd.getFile(s).getParent().addFile(d);
						made = true;
					}
			}
		}else if(s.split(" ").length == 2){
			if(cd.isInDirectory(s.split(" ")[0]) && cd.getFile(s.split(" ")[0]) instanceof File){
				if(cd.isInDirectory(s.split(" ")[1]) && cd.getFile(s.split(" ")[1]) instanceof Directory){
					boolean made = false;
					if(!((Directory) cd.getFile(s.split(" ")[1])).isInDirectory(s.split(" ")[0])){
						File d = new File(s.split(" ")[0], (Directory) cd.getFile(s.split(" ")[1]));
						((Directory) cd.getFile(s.split(" ")[1])).addFile(d);
						made = true;
					}
					for(int i = 1; made == false; i++)
						if(!((Directory) cd.getFile(s.split(" ")[1])).isInDirectory(s.split(" ")[0] + " (" + i + ")")){
							File d = new File(s.split(" ")[0] + " (" + i + ")", (Directory) cd.getFile(s.split(" ")[1]));
							((Directory) cd.getFile(s.split(" ")[1])).addFile(d);
							made = true;
						}
				}
			}
		}else System.out.println("Invalid syntax. e.g. cp <filename> [destination]");
	}
	
	public static void view(String s){
		if(Base.currentDir.isInDirectory(s) && Base.currentDir.getFile(s) instanceof BasicFile){
			System.out.println(((BasicFile) Base.currentDir.getFile(s)).getContents());
		}else System.out.println("Invalid file name.");
	}
	
}
