package com.hackyguys.hackallthethings.terminal;

import com.hackyguys.hackallthethings.filesystem.Directory;

public class FileTree{
	
	public final FileArchitecture ARCHITECTURE;
	public final Directory ROOT;
	
	public FileTree(FileArchitecture achitecture, Directory root){
		ARCHITECTURE = achitecture;
		ROOT = root;
	}
	
}
