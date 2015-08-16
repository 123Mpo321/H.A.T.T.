package com.hackyguys.hackallthethings.filesystem;

public class LuaFile extends BasicFile{

	{extention = "lua";}
	
	public LuaFile(String name, Directory parent){
		super(name, parent);
	}
	
	public LuaFile(String name, Directory parent, String contents){
		super(name, parent, contents);
	}
	
}
