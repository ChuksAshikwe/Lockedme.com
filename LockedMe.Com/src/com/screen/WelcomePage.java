package com.screen;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.services.DirectoryService;
import com.services.ServiceScreen;

public class WelcomePage implements Screen{
	
	//Scanner in = new Scanner(System.in);
	
	private String welcomeText = "Application Name: LockedMe.com";
	private String developerText = "Developer Name: Chuks Ashikwe";

	private ArrayList<String> options = new ArrayList<>();

	public WelcomePage() {

		options.add("1: Show Files");
		options.add("2: File Operations");
		options.add("3: Exit");

	}

	public void homeScreen() {
		System.out.println("Welcome Screen ");
		System.out.println("................................ ");
		System.out.println(welcomeText);
		System.out.println(" ");
		System.out.println(developerText);
		System.out.println("................................ ");
		Show();
		System.out.println("................................ ");
	}

	@Override
	public void Show() {
		System.out.println("\nMain Menu:");
		for (String s : options) {
			System.out.println(s);
		}

	}

	public void UserInput() {
		int selectedOption = 0;
		while ((selectedOption = this.getOption()) != 3) {
			this.NavigateOption(selectedOption);
		}
	}

	@Override
	public void NavigateOption(int option) {
		switch (option) {

		case 1: // Show Files in Directory
			this.ShowFiles();

			this.Show();

			break;

		case 2: // Show File Options menu
			ServiceScreen.setCurrentScreen(ServiceScreen.FileOptionsScreen);
			ServiceScreen.getCurrentScreen().Show();
			ServiceScreen.getCurrentScreen().UserInput();

			this.Show();

			break;

		default:
			System.out.println("Invalid Option please enter the number 1 to 3");
			break;
		}

	}

	public void ShowFiles() {

		// Get the files from the Directory
		System.out.println("List of Files: \n");
		DirectoryService.PrintFiles();
	}

	private int getOption() {
		
		Scanner in = new Scanner(System.in);
		int returnOption = 0;
		try {
			returnOption = in.nextInt();
		} catch (InputMismatchException ex) {

		}
		return returnOption;

	}

}
