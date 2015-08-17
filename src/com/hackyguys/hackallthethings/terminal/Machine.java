package com.hackyguys.hackallthethings.terminal;

import com.hackyguys.hackallthethings.terminal.os.OperatingSystem;

public abstract class Machine{
	
	public final String NAME;
	public final OperatingSystem OS;
	
	public Machine(String name, OperatingSystem os){
		NAME = name;
		OS = os;
	}
	
}
