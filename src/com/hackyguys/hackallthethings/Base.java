package com.hackyguys.hackallthethings;

import java.io.PrintStream;

import javafx.application.Application;
import javafx.stage.Stage;

import com.hackyguys.hackallthethings.commands.Command;
import com.hackyguys.hackallthethings.commands.Commands;
import com.hackyguys.hackallthethings.filesystem.Directory;
import com.hackyguys.hackallthethings.terminal.Machine;
import com.hackyguys.hackallthethings.terminal.SimpleMachine;
import com.hackyguys.hackallthethings.terminal.os.SimpleOS;

public class Base extends Application{
	
	public static final PrintStream STDOUT = System.out;
	
	public static Stage window;
	public static Machine currentMachine;
	public static Directory rootDirectory;
	public static Directory currentDir;
	
	public static void main(String[] args){;
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		window = primaryStage;
		window.setTitle("Terminal V1.0 (Alpha)");
		
		setMachine(new SimpleMachine("Simple Computer", new SimpleOS("Simple Operating System")));
		
		Base.currentDir = rootDirectory; // TODO Remove when login is implemented
		
		/* ******************************************************************* */
		/* ************************ Test Directories ************************ */
		
		
		
		/* ********************** End Test Directories *********************** */
		/* ******************************************************************* */
		
		window.setScene(currentMachine.OS.getTerminalScene());
		window.show();
	}
	
	public void setMachine(Machine machine){
		Base.currentMachine = machine;
		Base.rootDirectory = machine.OS.getRootDirectory();
		machine.init();
	}
	
	public static boolean enterCommand(String s){
		String primaryCommand = s.split(" ")[0];
		Command c = Commands.standardCommandMap.get(primaryCommand);
		if(c != null) c.call(s.substring(s.indexOf(' ') + 1));
		else if(currentDir.contains(primaryCommand)){
			currentDir.getFile(primaryCommand).open(s.substring(s.indexOf(" ")));
		}else return false;
		return true;
	}
}
