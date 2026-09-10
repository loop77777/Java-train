package day5;

public class ContinueDemo {

    public static void main(String[] args) {

        for(int rollNo = 1;
            rollNo <= 6;
            rollNo++) {

            if(rollNo == 4) {
                continue;
            }

            System.out.println(
                    "Attendance Marked For Roll No "
                            + rollNo);
        }
    }
}

