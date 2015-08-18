package com.hackyguys.hackallthethings.filesystem;

import java.io.FileNotFoundException;
import java.util.Scanner;

import com.hackyguys.hackallthethings.Base;
import com.hackyguys.hackallthethings.filesystem.fileexceptions.DirectoryImportException;
import com.hackyguys.hackallthethings.filesystem.fileexceptions.FileTreeImportException;

public class FileUtils{
	
	public static Directory loadFileTree(String file) throws FileNotFoundException, DirectoryImportException, FileTreeImportException{
		java.io.File f = new java.io.File(file);
		@SuppressWarnings("resource")
		Scanner input = new Scanner(f);
		Directory root = new Directory(input.nextLine().trim(), null);
		for(String cPDir = input.nextLine(); cPDir != "END_DIRS"; cPDir = input.nextLine())
			if(!cPDir.contains("/")) ;//root.addFile(new Directory(cPDir, root));
			else{
				Directory d = getDirectoryFromPath(cPDir.substring(0, cPDir.lastIndexOf("/")), root);
				if(d == null) throw new DirectoryImportException();
				//else d.addFile(new Directory(cPDir.substring(cPDir.lastIndexOf("/") + 1), d));
			}
		// TODO Finish importing.
		return root;
	}
	
	public static Directory getDirectoryFromPath(String path){
		return getDirectoryFromPath(path, Base.rootDirectory);
	}
	
	public static Directory getDirectoryFromPath(String path, Directory root){
		String[] dirs = path.split("/");
		Directory dir = (path.startsWith("~/")) ? Base.rootDirectory : Base.currentDir;
		for(int i = (path.startsWith("~/")) ? 1 : 0; i < dirs.length; i++)
			if(dir.contains(dirs[i])) ;//dir = (Directory) dir.getFile(dirs[i]);
			else return null;
		if(dir.getName() == path.substring(path.lastIndexOf("/") + 1)) return dir;
		return null;
	}
}
