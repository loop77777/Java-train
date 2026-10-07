package day10.PassingAndReturning.ConstructorChaining;

public class GrandFather
{
    public GrandFather(int a) {
        System.out.println("GrandFather Class parameterised Constructor");
    }
    public GrandFather()
    {
        System.out.println("GrandFather Class no argument Constructor");
    }
}

class TestMain
{
    public static void main(String[] args) {

        //Father f=new Father();
        GrandFather s=new Father(10);
        GrandFather s1=new Father();
    }
}