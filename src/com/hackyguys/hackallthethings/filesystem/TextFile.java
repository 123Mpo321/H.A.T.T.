package com.hackyguys.hackallthethings.filesystem;

public class TextFile extends BasicFile{
	
	public TextFile(String name, Directory parent){
		this(name, parent, null);
	}
	
	public TextFile(String name, Directory parent, String contents){
		super(name, parent, contents);
		extention = "txt";
		type = FileTypes.LUA;
	}
	
}
