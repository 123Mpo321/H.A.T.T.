package com.hackyguys.hackallthethings.commands;

import java.util.UUID;

public interface Command{
	
	public final UUID uniqueID = UUID.randomUUID();
	
	public void call(String s);
	
}
