package day11.demo.ABExtra;

public class B extends A
{
    public B()
    {
        System.out.println("B class Constructor");
    }
    public void show()
    {
        System.out.println("Show method in inherited class");
    }
    public static void main(String[] args) {
        A obj=new B();
        obj.display();
        obj.show();
    }
}
