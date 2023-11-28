package com.Bibhu.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadFilesutils {
	
	public Properties loadProperties() throws FileNotFoundException {
		Properties prop = new Properties();
		File filepath = new File("C:\\Users\\Bibhu\\WebAutomation\\ConfigFiles\\configuration.properties");
		FileInputStream fin = new FileInputStream(filepath);

		try {
			prop.load(fin);
//			System.out.println("UN:"+prop.get("userName")+"\nPWD:"+prop.getProperty("Password")+"\nURL::"+prop.getProperty("URL"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;

	}

	
	public Properties readConfig2() {
		Properties properties = new Properties();
		InputStream inputStream = ReadFilesutils.class.getClassLoader().getResourceAsStream("configuration.properties");
		if (inputStream != null) {
			try {
				properties.load(inputStream);
//				System.out.println("UN:" + properties.get("userName") + "\nPWD:" + properties.getProperty("Password")
//						+ "\nURL::" + properties.getProperty("URL"));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return properties;
	}
}
