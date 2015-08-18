package com.hackyguys.hackallthethings.terminal.os;

import java.io.PrintStream;
import java.util.HashMap;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import com.hackyguys.hackallthethings.Base;
import com.hackyguys.hackallthethings.ConsoleOutputStream;
import com.hackyguys.hackallthethings.filesystem.Directory;
import com.hackyguys.hackallthethings.terminal.FileTree;
import com.hackyguys.hackallthethings.terminal.os.user.User;


public abstract class OperatingSystem{
	
	public final String NAME;
	private FileTree fileTree;
	private HashMap<String, String> users;
	
	private Scene terminalScene;
	private Scene editScene;
	private TextField tf;
	private TextArea ta, eta;
	public static ConsoleOutputStream cos;
	
	public OperatingSystem(String name, FileTree fileTree){
		NAME = name;
		this.fileTree = fileTree;
		users = new HashMap<>();
		
		ta = new TextArea();
		tf = new TextField();
		
		ta.setEditable(false);
		ta.setOnKeyPressed(e -> {
			tf.requestFocus();
			tf.appendText(e.getCharacter());
		});
		ta.onKeyPressedProperty().addListener((obj, oldP, newP) -> {
		});
		ta.setStyle("-fx-focus-color: transparent;-fx-faint-focus-color: transparent;");
		VBox.setVgrow(ta, Priority.ALWAYS);
		cos = new ConsoleOutputStream(ta);
		
		tf.setOnAction(e -> {
			System.out.println(">" + ((TextField) e.getSource()).getText());
			if(!Base.enterCommand(((TextField) e.getSource()).getText())) System.out.println("Invalid Command!");
			tf.clear();
		});
		tf.setStyle("-fx-focus-color: transparent;-fx-faint-focus-color: transparent;");
		
		VBox box = new VBox(ta, tf);
		terminalScene = new Scene(box, 500, 350);
		
		eta = new TextArea();
		eta.setMinWidth(500);
		eta.setMinHeight(350);
		
		StackPane pane = new StackPane(eta);
		editScene = new Scene(pane);
		
	}
	
	public User addUser(String username, String password){
		User user = new User(username, password);
		users.put(user.getUsername(), user.getPassword());
		return user;
	}
	
	public Directory getRootDirectory(){
		return fileTree.ROOT;
	}
	
	public Scene getTerminalScene(){
		return terminalScene;
	}
	
	public Scene getEditScene(){
		return editScene;
	}
	
	public void init(){
		System.setOut(new PrintStream(cos));
		Base.window.setScene(this.terminalScene);
		this.tf.requestFocus();
	}
	
}
