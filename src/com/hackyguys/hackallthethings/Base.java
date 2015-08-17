package com.hackyguys.hackallthethings;

import java.io.PrintStream;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

import com.hackyguys.hackallthethings.commands.Command;
import com.hackyguys.hackallthethings.commands.Commands;
import com.hackyguys.hackallthethings.filesystem.BasicFile;
import com.hackyguys.hackallthethings.filesystem.Directory;
import com.hackyguys.hackallthethings.filesystem.LuaFile;
import com.hackyguys.hackallthethings.terminal.Machine;
import com.hackyguys.hackallthethings.terminal.SimpleMachine;
import com.hackyguys.hackallthethings.terminal.os.SimpleOS;

public class Base extends Application{
	
	public static PrintStream stdout;
	
	public static Stage window;
	public static Machine currentMachine;
	public static Directory rootDirectory;
	public static Directory currentDir;
	public static ConsoleOutputStream cos;
	
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		window = primaryStage;
		window.setTitle("Terminal V1.0 (Alpha)");
		
		currentMachine = new SimpleMachine("Simple Computer", new SimpleOS("Simple Operating System"));
		rootDirectory = currentMachine.OS.getRootDirectory();
		currentDir = rootDirectory;
		
		TextArea ta = new TextArea();
		TextField tf = new TextField();
		ta.setEditable(false);
		ta.setMinHeight(350);
		ta.setMinWidth(500);
		ta.setOnKeyPressed(e -> {tf.requestFocus(); tf.appendText(e.getCharacter());});
		ta.onKeyPressedProperty().addListener((obj, oldP, newP) -> {});
		ta.setStyle("-fx-focus-color: transparent;-fx-faint-focus-color: transparent;");
		tf.setOnAction(e -> enterCommand(((TextField) e.getSource()).getText(), tf));
		tf.setStyle("-fx-focus-color: transparent;-fx-faint-focus-color: transparent;");
		VBox box = new VBox(ta, tf);
		Scene s = new Scene(box);
		
		stdout = System.out;
		System.setOut(new PrintStream(new ConsoleOutputStream(ta)));
		
		currentDir.addFile(new LuaFile("Test0", currentDir, "io.write(\"Hello World!\");io.flush();"));
		currentDir.addFile(new LuaFile("Test1", currentDir));
		currentDir.addFile(new LuaFile("Test2", currentDir));
		currentDir.addFile(new LuaFile("Test3", currentDir));
		Directory d = new Directory("TestDir", currentDir);
		currentDir.addFile(d);
		d.addFile(new LuaFile("Testing", d));
		Directory d0 = new Directory("TestDir0", currentDir);
		currentDir.addFile(d0);
		d0.addFile(new LuaFile("Testing0", d0));
		
		window.setScene(s);
		window.show();
		
		tf.requestFocus();
	}
	
	public void setMachine(Machine machine){
		this.currentMachine = machine;
		this.rootDirectory = machine.OS.getRootDirectory();
		this.currentDir = rootDirectory; // TODO Remove when login is implemented
	} 
	
	public void enterCommand(String s, TextField tf){
		String primaryCommand = s.split(" ")[0];
		Command c = Commands.standardCommandMap.get(primaryCommand);
		tf.clear();
		System.out.println("> " + s);
		if(c != null){
			c.call(s.substring(s.indexOf(' ') + 1));
		}else if(currentDir.contains(primaryCommand) && primaryCommand.endsWith(".lua")){
			Globals globals = JsePlatform.standardGlobals();
			LuaValue chunk = globals.load("" + ((BasicFile) currentDir.getFile(primaryCommand)).getContents());
			chunk.call();
		}else{
			System.out.println("Invalid Command!");
		}
	}
	
}
