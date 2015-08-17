package com.hackyguys.hackallthethings.filesystem;


public abstract class File{
	
	boolean isHidden = false;
	String name;
	FileTypes type;
	String path;
	Directory parent;
	
	public File(String name){
		this(name, null);
	}
	
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
	
	protected void setParent(Directory parent){
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
	
	public FileTypes getType(){
		return type;
	}
	
}
