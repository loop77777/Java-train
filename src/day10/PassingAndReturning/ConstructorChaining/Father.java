package day10.PassingAndReturning.ConstructorChaining;

public class Father extends GrandFather {
    Father()
    {
        System.out.println("Father class Constructor");
    }
    public Father(int a)
    {
        super(a);
        System.out.println("Father class with parameterised Constructor");
    }
}
