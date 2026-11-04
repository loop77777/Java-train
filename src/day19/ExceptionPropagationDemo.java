package day19;

// . Exception Propagation, Declarations, and Rethrowing
//
//When an exception occurs inside a method, the JVM unwinds the call stack searching for a matching catch block. If the current method does not catch it, the exception propagates up to the calling method.
//
//throws Clause: Part of the method contract, declaring to caller methods that an exception might occur.
//
//Rethrowing: Allows low-level layers (like DAOs) to catch an exception, perform local operations (logging, rollback), and rethrow it so upper layers (Controllers/UI) can present appropriate user responses.

import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionPropagationDemo {

    // Low-Level Layer: Logs and Rethrows
    public static void readDatabaseConfig(String path) throws IOException {
        try {
            if (path == null || path.isEmpty()) {
                throw new FileNotFoundException("Config path cannot be empty.");
            }
            // Simulating an I/O Failure
            throw new IOException("Storage hardware failed to respond.");
        } catch (IOException e) {
            System.err.println("[LOG System] Recorded low-level I/O failure: " + e.getMessage());
            throw e; // Rethrowing exception up the call stack
        }
    }

    // Mid-Level Layer: Delegates up
    public static void initializeServices() throws IOException {
        readDatabaseConfig(""); // Propagates up
    }

    // Top-Level Execution Layer: Catches final propagated exception
    public static void main(String[] args) {
        try {
            initializeServices();
        } catch (IOException e) {
            System.out.println("[UI Layer] Displaying Error Dialog to User: " + e.getMessage());
        }
    }
}