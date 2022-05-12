package com.screen;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.directory.Directory;
import com.services.ServiceScreen;

public class FileOptionScreen implements Screen {
	
	//Scanner in = new Scanner(System.in);

	private Directory dir = new Directory();

	private ArrayList<String> options = new ArrayList<>();

	public FileOptionScreen() {

		options.add("1: Add A File");
		options.add("2: Delete A File");
		options.add("3: Search A File");
		options.add("4: Return To Menu");

	}

	@Override
	public void Show() {
		System.out.println("\nFile Operations:");
		for (String op : options) {
			System.out.println(op);
		}

	}

	public void UserInput() {
		int selectedOption;
		while ((selectedOption = this.getOption()) != 4) {
			this.NavigateOption(selectedOption);
		}
	}

	@Override
	public void NavigateOption(int option) {

		switch (option) {

		case 1: // Add File
			this.AddFile();

			this.Show();
			break;
		case 2: // Delete File
			this.DeleteFile();

			this.Show();
			break;
		case 3: // Search File
			this.SearchFile();

			this.Show();
			break;
		case 4: // Return to Menu
			ServiceScreen.setCurrentScreen(ServiceScreen.WelcomeScreen);
			ServiceScreen.getCurrentScreen().Show();
			ServiceScreen.getCurrentScreen().UserInput();
			
			break;

		default:
			System.out.println("\nInvalid Option please enter the value 1 to 3");
			break;

		}

	}
	// Add Functionality
	public void AddFile() {
		System.out.print("\nPlease Enter Filename:");
		String fileName = this.getInputString();

		try {
			Path path = FileSystems.getDefault().getPath(Directory.name + fileName).toAbsolutePath();
			File file = new File(dir.getName() + fileName);

			if (file.createNewFile()) {
				dir.getFiles().add(file);
				System.out.println("The File " + fileName + " have been added successful..");

			} else {
				System.out.println("This File Already Exits.");
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	// Delete Functionality
	public void DeleteFile() {

		System.out.print("\nPlease Enter the Filename:");
		String fileName = this.getInputString();
		Path path = FileSystems.getDefault().getPath(Directory.name + fileName).toAbsolutePath();
		File file = path.toFile();
		if (file.delete()) {
			System.out.println("\nDeleted File: " + file.getName() + " have been deleted successful...");
			dir.getFiles().remove(file);
		} else {
			System.out.println("\nFile " + fileName + " not found.");
		}

	}

	public void SearchFile() {

		Boolean found = false;
		System.out.print("\nPlease Enter the Filename:");
		String fileName = this.getInputString();
		ArrayList<File> files = dir.getFiles();

		for (int i = 0; i < files.size(); i++) {
			if (files.get(i).getName().equals(fileName)) {
				System.out.println("File " + fileName + " Found successful...");
				found = true;
			}
		}
		if (found == false) {
			System.out.println("\nFile not found");
		}
	}

	private String getInputString() {
		Scanner in = new Scanner(System.in);
		return (in.nextLine());

	}

	private int getOption() {
		Scanner in = new Scanner(System.in);
		int returnOption = 0;
		try {
			returnOption = in.nextInt();
		} catch (InputMismatchException ex) {
			System.out.println("\nInvalid input");
		}
		return returnOption;

	}

}
