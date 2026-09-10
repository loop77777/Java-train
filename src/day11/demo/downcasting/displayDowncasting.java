package day11.demo.downcasting;

class Demo
{
    void display()
    {
        System.out.println("Welcome to Java Training");
    }

}
class Test extends Demo
{
    void show()
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