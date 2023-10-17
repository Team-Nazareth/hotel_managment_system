package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class FileHandler {
	File file;
	String filePath;
	boolean append;
	
	public FileHandler(String fPath) {
		filePath = fPath;
		
		File file = new File(filePath);
        try {
            if (file.createNewFile()) {
                System.out.println("File created successfully.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	public ArrayList<String[]> readMenu() {
		ArrayList<String[]> menuData = new ArrayList<>();
		String[] row = new String[2]; 
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                row = line.split(",");
                menuData.add(row);
            }
            
            reader.close();
            return menuData;
        } catch (IOException e) {
            e.printStackTrace();
            return menuData;
        }
	}
	
	public ArrayList<String> readRoom() {
		ArrayList<String> roomData = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                roomData.add(line);
            }
            reader.close();
            return roomData;
        } catch (IOException e) {
            e.printStackTrace();
            return roomData;
        }
	}
	
	public boolean write(String menu_id, int qty) {
		try {
            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            // Write characters to the file
            writer.write(menu_id +","+ qty);
            writer.newLine();

            // Close the writer
            writer.flush();
            writer.close();
            fileWriter.close();

            System.out.println("Data written to the file.");
    		return true;
        } catch (IOException e) {
            e.printStackTrace();
    		return false;
        }

	}
	
	public boolean write(String room_id) {
		try {
            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            // Write characters to the file
            writer.write(room_id);
            writer.newLine();
            // Close the writer
            writer.close();

            System.out.println("Data written to the file.");
    		return true;
        } catch (IOException e) {
            e.printStackTrace();
    		return false;
        }

	}
	
	public boolean deleteLine(String textToDelete) {
		List<String> lines = new ArrayList<>();

        try {
            // Read the CSV file into a list of lines
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> updatedLines = new ArrayList<>();

        // Identify and remove the lines containing the specified text
        for (String line : lines) {
        	System.out.println("line: "+ line);
            if (!line.contains(textToDelete)) {
            	System.out.println(textToDelete);
                updatedLines.add(line);
            }
        }

        try {
            // Write the updated data back to the CSV file
            Files.write(Paths.get(filePath), updatedLines);
            System.out.println("Lines deleted successfully.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
	}
}
