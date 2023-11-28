package com.Bibhu.dataProvider;

import org.testng.annotations.DataProvider;

public class Login_dataProvider {
	@DataProvider(name = "credentials")
	public Object[][] provideCredentials() {
		return new Object[][] { { "Test1", 12345 }, { "Test2", 123456 }, { "Test3", 1234567 } };
	}
}
