package com.hackyguys.hackallthethings.filesystem;

public class LuaFile extends BasicFile{
	
	public LuaFile(String name, Directory parent){
		this(name, parent, null);
	}
	
	public LuaFile(String name, Directory parent, String contents){
		super(name, parent, contents);
		extention = "lua";
		type = FileTypes.LUA;
	}
	
}
