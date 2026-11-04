package day19;

//15–16. Exceptions in API Design & Rethrowing
//
//Method Contract (throws): Declaring checked exceptions in your method signature clearly communicates requirements to API consumers.
//
//Rethrowing: Low-level layers (like DAOs) can catch an exception, log the technical details, and then rethrow it (or wrap it in a custom exception) so upper architectural layers (like UI/Controllers) can handle the response.

import java.io.IOException;

public class RethrowDemo {

    // DAO Layer
    public void executeQuery() throws IOException {
        try {
            throw new IOException("Database connection timed out.");
        } catch (IOException e) {
            System.err.println("[LOG - DAO Layer] Query failed. Logging trace locally...");
            throw e; // Rethrowing original exception up to Service Layer
        }
    }

    // Controller Layer
    public static void main(String[] args) {
        RethrowDemo service = new RethrowDemo();
        try {
            service.executeQuery();
        } catch (IOException e) {
            System.out.println("[UI Layer] Displayed to user: Service temporarily unavailable. Details: " + e.getMessage());
        }
    }
}