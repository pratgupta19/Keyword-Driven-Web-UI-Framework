package com.qa.hs.tests;

import java.io.FileNotFoundException;

import org.testng.annotations.Test;
import com.qa.hs.keyword.engine.KeyWordEngine;

public class LoginTest {
	
	public KeyWordEngine KeyWordEngine;
	
  @Test
  public void loginTest() throws FileNotFoundException {
	  KeyWordEngine = new KeyWordEngine();
	  KeyWordEngine.startExecution("login");
	  
	  
  }
  
  
  
}
