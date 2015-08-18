package com.hackyguys.hackallthethings.filesystem;

import java.util.ArrayList;
import java.util.Calendar;

public class Directory extends FSNode{
	
	ArrayList<FSNode> children;
	Directory parent;
	
	public Directory(String name, Directory parent){
		children = new ArrayList<>();
	}
	
	public void addFile(File f){
		this.children.add(f);
		f.path = this.path;
	}
	
	public void addFiles(File ... files){
		for(File f: files) addFile(f);
	}
	
	public FSNode[] getChildren(){
		FSNode[] result = new File[children.size()];
		for(int i = 0; i < children.size(); i++)
			result[i] = children.get(i);
		return result;
	}
	
	public File getFile(String s){
		for(FSNode f: children)
			if(f.getName().equals(s) && f instanceof File) return (File) f;
		return null;
	}
	
	public boolean deleteFile(String s){
		for(FSNode f: children)
			if(f.getName().equals(s) && f instanceof File){
				children.remove(f);
				return true;
			}
		return false;
	}
	
	public boolean deleteFile(File f){
		return children.remove(f);
	}
	
	public boolean contains(String s){
		return getFile(s) != null;
	}
	
	public boolean contains(File f){
		return children.contains(f);
	}

	@Override
	public String getName(){
		return name;
	}

	@Override
	public void setName(String s){
		
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
