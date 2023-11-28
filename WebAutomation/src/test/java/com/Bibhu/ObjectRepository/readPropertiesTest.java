package com.Bibhu.ObjectRepository;

import java.io.FileNotFoundException;

import org.testng.annotations.Test;

import com.Bibhu.Utils.ReadFilesutils;

public class readPropertiesTest {
	ReadFilesutils readFiles = new ReadFilesutils();

	@Test(enabled = true, description = "Read properties files using filepath")
	public void readConfigProperties1() throws FileNotFoundException {
		String UN = readFiles.loadProperties().getProperty("userName");
		String PWD = readFiles.loadProperties().getProperty("Password");
		String URL = readFiles.loadProperties().getProperty("URL");

		System.out.println("UserName::" + UN + "\nPassword::" + PWD + "\nurl::" + URL);
	}
	
	@Test(enabled = true, description = "Read properties files using resourceAsStream classloader")
	public void readConfigProperties2() throws FileNotFoundException {
		String UN = readFiles.readConfig2().getProperty("userName");
		String PWD = readFiles.readConfig2().getProperty("Password");
		String URL = readFiles.readConfig2().getProperty("URL");

		System.out.println("\nUserName::" + UN + "\nPassword::" + PWD + "\nurl::" + URL);
	}
}
