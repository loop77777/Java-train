package day18;

public class Demo4 {

    public static void main(String[] args) {

        try {

            int x = 10 / 0;

        } catch (Exception e) {

            System.out.println("Handled");
        } finally {

            System.out.println("Finally Block");
        }

        System.out.println("Program Continues");
    }
}
