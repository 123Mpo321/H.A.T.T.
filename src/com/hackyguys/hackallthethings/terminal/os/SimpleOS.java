package com.hackyguys.hackallthethings.terminal.os;

import com.hackyguys.hackallthethings.filesystem.Directory;
import com.hackyguys.hackallthethings.terminal.FileArchitecture;
import com.hackyguys.hackallthethings.terminal.FileTree;

public class SimpleOS extends OperatingSystem{

	public SimpleOS(String name){
		super(name, new FileTree(FileArchitecture.NILFS, new Directory("root", null)));
	}
	
}
