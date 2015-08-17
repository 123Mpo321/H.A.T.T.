package com.hackyguys.hackallthethings;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

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
		Method m = Commands.map.get(primaryCommand);
		tf.clear();
		System.out.println("> " + s);
		if(m != null){
			try{
				m.invoke(null, (Object) s.substring(s.indexOf(' ') + 1));
			}catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
				e.printStackTrace();
			}
		}else if(currentDir.contains(primaryCommand) && primaryCommand.endsWith(".lua")){
			LuaState L = LuaStateFactory.newLuaState();
			L.openLibs();
			L.LdoString("" + ((BasicFile) currentDir.getFile(primaryCommand)).getContents());
		}else{
			System.out.println("Invalid Command!");
		}
	}
	
}
