package com.proj.app;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.proj.controllers.*;
import com.proj.models.*;
import com.proj.utilites.*;
import com.proj.views.*;

@RunWith(Suite.class)
@SuiteClasses({ FortificationControllerTest.class, GameControllerTest.class, MapEditorControllerTest.class, NewGameControllerTest.class,
				ContinentTest.class, CountryTest.class, GameModelCreationTest.class, MapTest.class, PlayerTest.class,
				ConstantsTest.class, MapToolsTest.class,
				FortificationViewTest.class, GameWindowScreenTest.class, MainMenuScreenTest.class, MapEditorTest.class, 
				PlayNewGameTest.class, ToolBarTest.class})
public class AppTestSuite {
	

}
