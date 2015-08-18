package com.hackyguys.hackallthethings.filesystem;

import java.util.Calendar;


public class File extends FSNode{
	
	boolean isHidden = false;
	String content = "";
	String name;
	String path;
	Directory parent;
	
	public File(String name){
		this.name = name;
		this.path = "";
	}
	
	@Override
	public String getName(){
		return name;
	}
	
	@Override
	public void setName(String name){
		this.name = name;
	}
	
	protected String getLocation(){
		return path;
	}
	
	public void setLocation(String location){
		this.path = location;
	}
	
	public boolean getIsHidden(){
		return isHidden;
	}
	
	public void setIsHidden(boolean isHidden){
		this.isHidden = isHidden;
	}
	
	@Override
	public String getPath(){
		return path;
	}
	
	@Override
	public Calendar getModifyTime(){
		return modified;
	}
	
}
