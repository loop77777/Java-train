1. Data Types, Variables & Arrays

These are the building blocks of Java programming. Before learning classes, objects, collections, etc., you must clearly understand these three concepts.
1. What is a Data Type?
Definition

A Data Type specifies:

What kind of value can be stored.
How much memory is required.
What operations can be performed on it. 

int age = 25;

byte
short
int
long
float
double
char
boolean


int age = 25;
double price = 99.99;
char grade = 'A';
boolean passed = true;

public class DataTypeDemo {

    public static void main(String[] args) {

        int age = 25;

        double salary = 50000.50;

        char grade = 'A';

        boolean married = false;

        System.out.println(age);
        System.out.println(salary);
        System.out.println(grade);
        System.out.println(married);
    }
}



2. What is a Variable?
   Definition

A variable is a named memory location that stores a value.

int age = 25;

public class VariableDemo {

    public static void main(String[] args) {

        int age = 25;

        System.out.println(age);

        age = 30;

        System.out.println(age);
    }
}


age
studentName
salary123
_amount
Invalid-->123age
class
student-name
`
 Variable Life Cycle

 Step 1: Declaration
 int age;

 Step 2: Initialization
 age = 25;

 Step 3: Usage
 System.out.println(age);
 Definition

An day2.array is a fixed-size collection of elements of the same datatype.


 3. What is an Array?
Problem Without Arrays

Suppose a student has 5 marks.
int mark1 = 90;
int mark2 = 80;
int mark3 = 70;
int mark4 = 60;
int mark5 = 50;

 Array stores multiple values of the same datatype.
 int[] marks = {90,80,70,60,50};

int mark1 = 90;
int mark2 = 80;
int mark3 = 70;
int mark4 = 60;
int mark5 = 50;

 Array stores multiple values of the same datatype.
 int[] marks = {90,80,70,60,50};
 marks
|
v

 ---------------------
|90|80|70|60|50|
---------------------
0  1  2  3  4

public class ArrayDemo {

    public static void main(String[] args) {

        int[] marks = {90, 80, 70, 60, 50};

        System.out.println("First Mark = " + marks[0]);

        System.out.println("Second Mark = " + marks[1]);

        System.out.println("Third Mark = " + marks[2]);
    }
}

public class ArrayLoopDemo {

    public static void main(String[] args) {

        int[] marks = {90,80,70,60,50};

        for(int i = 0; i < marks.length; i++) {

            System.out.println(marks[i]);
        }
    }
}

public class DataTypeAndArrayDemo {
public static void main(String[] args) {
// Primitive Data Types
byte age = 25;
short pinCode = 5600;
int salary = 50000;
long population = 1400000000L;
float height = 5.8f;
double accountBalance = 12345.67;
char grade = 'A';
boolean isAvailable = true;

        // Array
        int[] marks = {90, 80, 70};

        System.out.println("Age: " + age);
        System.out.println("First Mark: " + marks[0]);
    }
}

// next topics - day2

