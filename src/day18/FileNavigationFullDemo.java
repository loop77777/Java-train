package day18;

import java.io.File;
import java.io.IOException;

// java.io.File represents abstract file and directory pathnames. It does not read or write file payload data directly; it handles structural metadata operations (creation, existence checks, deletion, directory tree listing, and path resolution).

public class FileNavigationFullDemo {
    public static void main(String[] args) throws IOException {
        File folder = new File("ride_records");
        if (!folder.exists()) {
            folder.mkdir();
        }

        File logFile = new File(folder, "trip_logs.txt");
        boolean created = logFile.createNewFile();

        System.out.println("File created: " + created);
        System.out.println("Exists: " + logFile.exists());
        System.out.println("Absolute Path: " + logFile.getAbsolutePath());
        System.out.println("Is Directory: " + logFile.isDirectory());
        System.out.println("File Size (bytes): " + logFile.length());

        // Cleanup
        logFile.delete();
        folder.delete();
    }
}

