package day10.PassingAndReturning.ConstructorDemoizing;

class GrandFather
{
    public GrandFather(int a,int b) {
        this();
        System.out.println("GrandFather Class 2 parameterised Constructor");
    }
    public GrandFather(int a) {
        this(a,1);
        System.out.println("GrandFather Class parameterised Constructor");
    }
    public GrandFather()
    {
        System.out.println("GrandFather Class no argument Constructor");
    }
}

class Father extends day10.PassingAndReturning.ConstructorChaining.GrandFather {
    Father()
    {super(1);
        System.out.println("Father class Constructor");
    }
    Father(int a)
    {
        this();
        System.out.println("Father class with parameterised Constructor");
    }
}

class TestMain
{
    public static void main(String[] args) {

        //Father f=new Father();
        day10.PassingAndReturning.ConstructorChaining.GrandFather s=new day10.PassingAndReturning.ConstructorChaining.Father(10);

    }
}