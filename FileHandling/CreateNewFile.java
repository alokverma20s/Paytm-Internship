import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class CreateNewFile {
    public static void main(String[] args) {

        // Create a file
        /*
        File file = new File("text.txt");
        try {
            if(file.createNewFile()){
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
        */


        // Write to a file
        /*
        try {
            System.out.println("Writing to file...");
            FileWriter fileWriter = new FileWriter("text.txt");
            fileWriter.write("Hello World!");
            fileWriter.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
        */

        // Reading from a file
        /* 
        try{
            System.out.println("Reading from file...");
            File file = new File("text.txt");
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                String data = sc.nextLine();
                System.out.println(data);
            }
            sc.close();
        } catch(Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
        */

        // Delete a file
        try {
            File file = new File("text.txt");
            if(file.delete()){
                System.out.println("File deleted: " + file.getName());
            } else {
                System.out.println("File not found.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }
}
