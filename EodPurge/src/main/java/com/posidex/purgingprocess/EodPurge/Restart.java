package com.posidex.purgingprocess.EodPurge;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Restart {
	public static void restartApplication()
	{
	  final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
	  System.out.println("1 ::: "+javaBin);
	  File currentJar = null;
	try {
		currentJar = new File(Restart.class.getProtectionDomain().getCodeSource().getLocation().toURI());
		System.out.println("2 :::"+currentJar.getName());
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	  /* is it a jar file? */
	  if(!currentJar.getName().endsWith(".jar"))
	    return;

	  /* Build command: java -jar application.jar */
	  final ArrayList<String> command = new ArrayList<String>();
	  command.add(javaBin);
	  command.add("-jar");
	  command.add(currentJar.getPath());

	  final ProcessBuilder builder = new ProcessBuilder(command);
	  try {
		builder.start();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  System.exit(0);
	}
	public static void main(String[] args) {
		restartApplication();
	}
}
