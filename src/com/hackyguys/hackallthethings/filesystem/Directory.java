package com.hackyguys.hackallthethings.filesystem;

import java.util.ArrayList;

public class Directory extends File{
	
	ArrayList<File> children;
	
	public Directory(String name, Directory parent){
		super(name, parent);
		children = new ArrayList<File>();
		type = FileTypes.DIRECTORY;
	}
	
	public void addFile(File f){
		this.children.add(f);
		f.setParent(this);
	}
	
	public void addFiles(File ... files){
		for(File f: files){
			if(this.parent != null && this.parent.contains(f.getName(), f.getType())){
				this.children.add(f);
				f.setParent(this);
			}
		}
	}
	
	public File[] getChildren(){
		File[] result = new File[children.size()];
		for(int i = 0; i < children.size(); i++)
			result[i] = children.get(i);
		return result;
	}
	
	public File getFile(String s){
		for(File f: children)
			if(f.getName().equals(s)) return f;
		return null;
	}
	
	public File getFile(String s, FileTypes type){
		for(File f: children)
			if(f.getName().equals(s) && f.getType().equals(type)) return f;
		return null;
	}
	
	public boolean deleteFile(String s){
		for(File f: children)
			if(f.getName().equals(s)){
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
	
	public boolean contains(String s, FileTypes type){
		return getFile(s, type) != null;
	}
	
	public boolean contains(File f){
		return children.contains(f);
	}
	
}
