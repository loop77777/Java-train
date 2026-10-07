package day11.demo.downcasting;

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
