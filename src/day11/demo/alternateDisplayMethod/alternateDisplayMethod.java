package day11.demo.alternateDisplayMethod;

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
        super.display();
        System.out.println("Display method in child class");
    }
    public static void main(String[] args) {
        Demo d=new Test();
        d.display();//Upcasting


    }
}