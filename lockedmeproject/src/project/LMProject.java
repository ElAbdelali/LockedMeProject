//Abdelali Eljaouhari
//LockedMe Application
//1/18/2021

package project;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class LMProject 
{

    final static String FOLDER = "/tmp";

    static Scanner scanner = new Scanner(System.in);

    public static void main( String[] args )
    {
        showWelcomeScreen();
        showMainMenu();

    }
    
    private static void searchForAFile() {
    	System.out.print("Please enter the file you are searching for: ");
    	String filePath = scanner.nextLine();
    	if(!Files.exists(Paths.get(filePath))) {
    		System.out.println("File not found!");
    		return;
    	}else { 
    		System.out.println("File found! Path: " + filePath);
    	}
    }
    
    private static void deleteAFile() {
    	System.out.print("Please enter a file path: ");
    	String fileInput = scanner.nextLine();
   	try {
   		boolean a; 
    	a = Files.deleteIfExists(Paths.get(fileInput ));
    	if (!a) {
    		System.out.println("No such file/directory exists!");
    	} else {
    		System.out.println("Deletion successful!");
    	}
		} catch (IOException e) {
			System.out.println("Invalid permissions.");
		}
    }
    private static void collectMainMenuOption() {
        System.out.print("Please choose option 1, 2 or 3:");
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                showFilesInAscendingOrder();
                break;
            case "2":
                showFileOperations();
            case "3":
                System.out.println("Thanks for using lockedme.com! Closing application.");
                System.exit(0);
                break;
            default:
                System.out.println("Input provided was invalid, please choose from the following values: 1, 2 or 3.");
        }
        showMainMenu();
    }

    private static void showFileOperations() {
        System.out.println("--------------");
        System.out.println("1.) Add a file");
        System.out.println("2.) Delete a file");
        System.out.println("3.) Search for a file");
        System.out.println("4.) Back to main menu");
        System.out.println("--------------");
        collectFileOperation();
    }

    private static void addAFile() throws InvalidPathException {
        System.out.print("Please provide a file path: ");
        String filePath = scanner.nextLine();
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            System.out.println("File does not exist");
            return;
        }

        String newFilePath = FOLDER + "/" + path.getFileName();
        int inc = 0;
        while (Files.exists(Paths.get(newFilePath))) {
            inc++;
            newFilePath = FOLDER + "/" + inc + "_" + path.getFileName();
        }
        try {
            Files.copy(path, Paths.get(newFilePath));
        } catch(IOException e) {
            System.out.println("Unable to copy file to " + newFilePath);
        }

    }

    private static void collectFileOperation() {
        System.out.println("Please choose 1, 2, 3 or 4:");
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                addAFile();
                break;
            case "2":
            	deleteAFile();
                break;
            case "3":
            	searchForAFile();
                break;
            case "4":
            	showMainMenu();
                break;
        }
        showFileOperations();
    }

    private static void showFilesInAscendingOrder() {
        System.out.println("------------------");
        System.out.println("Displaying files in ascending order: ");
        System.out.println("------------------");
        File[] files = new File(FOLDER).listFiles();
        Set<String> sorted = new TreeSet<>();
        for (File file: files) {
            if (!file.isFile()) {
                continue;
            }
            sorted.add(file.getName());
        }
        sorted.forEach(System.out::println);
        System.out.println("------------------");
    }

    private static void showMainMenu() {
        System.out.println("-- MAIN MENU --");
        System.out.println("1.) Show files in ascending order");
        System.out.println("2.) Perform file operations");
        System.out.println("3.) Close the application");
        System.out.println("---------------");
        collectMainMenuOption();
    }

    private static void showWelcomeScreen() {
        System.out.println("---------------");
        System.out.println("Application: LockedMe.com");
        System.out.println("Developer: Abdelali Eljaouhari");
        System.out.println("---------------");
    }
}
