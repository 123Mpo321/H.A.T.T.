package com.hackyguys.hackallthethings.filesystem;

public class File{
	
	protected boolean isHidden = false;
	protected String name;
	protected String path;
	protected Directory parent;
	
	public File(String name, Directory parent){
		this.name = name;
		this.parent = parent;
		Directory d = parent;
		this.path = "";
		while(d != null && d.parent != null){
			this.path = d.getName() + "/" + this.path;
			d = d.parent;
		}
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public Directory getParent(){
		return parent;
	}
	
	public void setParent(Directory parent){
		this.parent = parent;
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
	
}
