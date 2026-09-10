package day11.demo.displayMethod;

class Demo
{
    void display()
    {
        System.out.println("Display method in parent class");
    }

}
class Test extends Demo
{
    void display()
    {
        System.out.println("Display method in child class");
    }
    public static void main(String[] args) {
        Demo d=new Test();
        d.display();//Upcasting


    }
}