package com.proj.app;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.proj.controllers.*;
import com.proj.models.*;
import com.proj.utilites.*;
import com.proj.views.*;

/**
 * Main test suite of all test classes
 */
@RunWith(Suite.class)
@SuiteClasses({ AggressiveTest.class, BenevolentTest.class, CheaterTest.class, RandomPlayerTest.class, TournamentControllerTest.class, MapTest.class, MapToolsTest.class, NewGameControllerTest.class, GameControllerTest.class, AttackControllerTest.class, FortificationControllerTest.class, UploadExistingGameControllerTest.class})
public class AppTestSuite {}
