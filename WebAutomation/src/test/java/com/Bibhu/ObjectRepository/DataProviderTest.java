package com.Bibhu.ObjectRepository;

import org.testng.annotations.Test;

import com.Bibhu.dataProvider.Login_dataProvider;

public class DataProviderTest {
	@Test(dataProvider = "credentials",dataProviderClass = Login_dataProvider.class)
	public void receiveTestData(String userName, int password) {
		System.out.println("Name::" + userName + " Password::" + password);
	}
}
