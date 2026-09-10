package day11.demo.ABExtra;

abstract class A
{
    A()
    {
        System.out.println("A class Constructor");
    }
    void display()
    {
        System.out.println("Display method in Parent class");
    }
    abstract void show();
}
class B extends A
{
    B()
    {
        System.out.println("B class Constructor");
    }
    void show()
    {
        System.out.println("Show method in inherited class");
    }
    public static void main(String[] args) {
        A obj=new B();
        obj.display();
        obj.show();
    }
}