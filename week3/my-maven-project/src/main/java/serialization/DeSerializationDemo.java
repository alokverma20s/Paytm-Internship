package serialization;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class DeSerializationDemo {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("text.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            Student s1 = (Student) ois.readObject();
            System.out.println("Name: "+s1.getName());
            System.out.println("Age: "+s1.getAge());
            System.out.println("RollNo: "+s1.getRollNo());

            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }
}