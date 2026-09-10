package day10.PassingAndReturning.ConstructorDemo;

class GrandFather
{
    public GrandFather(int a) {
        System.out.println("GrandFather Class Constructor");
    }
}

class Father extends day10.PassingAndReturning.GrandFather {
    Father()
    {
        super(10);
        System.out.println("Father class Constructor");
    }
}
class Son extends day10.PassingAndReturning.Father {
    Son()
    {//super();
        System.out.println("Son class Constructor");
    }
}
class TestMain
{
    public static void main(String[] args) {

        //Father f=new Father();
        day10.PassingAndReturning.GrandFather s=new Son();
    }
}