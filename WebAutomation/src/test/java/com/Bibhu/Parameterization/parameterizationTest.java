package com.Bibhu.Parameterization;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class parameterizationTest {
@Parameters({"UserName", "Password"})
@Test(groups = {"SanityTest"})
public void testParameterAtSuiteLevel(String UserName,String Password) {
	System.out.println("UN::"+UserName+"PWD::"+Password);
}
}
