package com.hackyguys.hackallthethings.filesystem;

import java.util.Calendar;

public abstract class FSNode{
	
	protected String name, path, metadata;
	protected Calendar modified;
	
	public abstract String getName();
	
	public abstract void setName(String s);
	
	public abstract String getPath();
	
	public abstract Calendar getModifyTime();
	
	public void open(){
	}
	
	public void open(String s){
	}
	
}
