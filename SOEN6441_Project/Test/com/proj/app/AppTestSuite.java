package com.proj.app;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.proj.controllers.*;
import com.proj.models.*;
import com.proj.utilites.*;
import com.proj.views.*;

@RunWith(Suite.class)
@SuiteClasses({ MapTest.class, MapToolsTest.class, NewGameControllerTest.class})
public class AppTestSuite {
	

}
