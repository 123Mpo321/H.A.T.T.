package com.hackyguys.hackallthethings;

import java.io.IOException;
import java.io.OutputStream;

import javafx.scene.control.TextArea;

public class ConsoleOutputStream extends OutputStream{
	
	private TextArea textArea;
	
	public ConsoleOutputStream(TextArea textArea){
		this.textArea = textArea;
	}
	
	@Override
	public void write(int b) throws IOException{
		textArea.appendText(String.valueOf((char) b));
		textArea.end();
	}
	
}
