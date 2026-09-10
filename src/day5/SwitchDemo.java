package day5;

public class SwitchDemo {

    public static void main(String[] args) {

        int channel = 3;

        switch(channel) {

            case 1:
                System.out.println("News Channel");
                break;

            case 2:
                System.out.println("Sports Channel");
                break;

            case 3:
                System.out.println("Movie Channel");
                break;

            default:
                System.out.println("Invalid Channel");
        }
    }
}

