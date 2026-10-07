package day11.demo.ABExtra;

public abstract class A
{
    protected A()
    {
        System.out.println("A class Constructor");
    }
    public void display()
    {
        System.out.println("Display method in Parent class");
    }
    public abstract void show();
}
