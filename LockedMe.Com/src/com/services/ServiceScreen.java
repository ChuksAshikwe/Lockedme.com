package com.services;

import com.screen.FileOptionScreen;
import com.screen.Screen;
import com.screen.WelcomePage;

public class ServiceScreen {
	
	public static WelcomePage WelcomeScreen = new WelcomePage();
    public static FileOptionScreen FileOptionsScreen = new FileOptionScreen();
    
    

    public static Screen CurrentScreen = WelcomeScreen;

    
    public static Screen getCurrentScreen() {
        return CurrentScreen;
    }

    
    public static void setCurrentScreen(Screen currentScreen) {
        CurrentScreen = currentScreen;
    }

}
