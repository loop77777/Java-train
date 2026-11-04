package day18;

//Byte Streams Hierarchy & Working Demo
//Byte streams process raw binary values (0 to 255). When reading a byte stream, the -1 return code indicates the End-Of-File (EOF) signal.

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamDemo {
    public static void main(String[] args) {
        String filename = "driver_avatar.dat";

        // Writing raw bytes using FileOutputStream
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            byte[] binaryData = { 0x4A, 0x41, 0x56, 0x41, 100, 120 }; // Raw byte values
            fos.write(binaryData);
            System.out.println("Binary data written successfully.");
        } catch (IOException e) {
            System.err.println("Write error: " + e.getMessage());
        }

        // Reading raw bytes using FileInputStream
        try (FileInputStream fis = new FileInputStream(filename)) {
            int byteRead;
            System.out.print("Bytes read from disk: ");
            while ((byteRead = fis.read()) != -1) {
                System.out.print(byteRead + " ");
            }
            System.out.println();
        } catch (IOException e) {
            System.err.println("Read error: " + e.getMessage());
        }
    }
}
