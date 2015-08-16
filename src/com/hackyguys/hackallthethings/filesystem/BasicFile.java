package com.hackyguys.hackallthethings.filesystem;

public class BasicFile extends File{
	
	protected String contents;
	protected String extention;
	
	public BasicFile(String name, Directory parent){
		super(name, parent);
	}
	
	public BasicFile(String name, Directory parent, String contents){
		super(name, parent);
		this.contents = contents;
	}
	
	public String getName(){
		return name + "." + extention;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setContents(String contents){
		this.contents = contents;
	}
	
	public String getContents(){
		return contents;
	}
	
}
