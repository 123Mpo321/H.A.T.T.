package com.hackyguys.hackallthethings.filesystem;

import java.util.ArrayList;

public class Directory extends File{
	
	ArrayList<File> children;
	
	public Directory(String name, Directory parent){
		super(name, parent);
		children = new ArrayList<File>();
	}
	
	public void addFile(File f){
			this.children.add(f);
	}
	
	public ArrayList<File> getChildren(){
		return children;
	}
	
	public File getFile(String s){
		for(File f: children)
			if(f.getName().equals(s)) return f;
		return null;
	}
	
	public boolean isInDirectory(String s){
		return getFile(s) != null;
	}
	
	public boolean isInDirectory(File f){
		return children.contains(f);
	}
	
}
