package day18;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Demo5 {

    public static void main(String[] args) {

        try {

            FileInputStream fis =
                    new FileInputStream("emp.txt");

        } catch (FileNotFoundException e) {

            System.out.println("File Not Found");
        }
    }
}

