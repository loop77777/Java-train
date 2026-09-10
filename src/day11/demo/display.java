package day11.demo;

class Demo
{
    void display()
    {
        System.out.println("Welcome to Java Training");
    }
    void show()
    {
        System.out.println("Show method");
    }
}
class Test extends day11.demo.downcasting.Demo
{
    public static void main(String[] args) {
        day11.demo.downcasting.Demo d=new day11.demo.downcasting.Test();
        d.display();//Upcasting
        d.show();

    }
}