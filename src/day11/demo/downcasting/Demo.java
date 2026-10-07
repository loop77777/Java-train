package day11.demo.downcasting;

public class Demo
{
    public void display()
    {
        System.out.println("Welcome to Java Training");
    }

}
public class Test extends Demo
{
    public void show()
    {
        System.out.println("Show method");
    }
    public static void main(String[] args) {
        Demo d=new Test();
        d.display();//Upcasting
        Test t=(Test)d;//Downcasting
        t.show();

    }
}