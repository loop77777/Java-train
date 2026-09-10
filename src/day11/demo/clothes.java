package day11.demo;

class Clothes
{
    double price=1500.00,gst=0.18;
    void color()
    {
        System.out.println("Yellow");
    }
    void size()
    {
        System.out.println("Small");
    }
    void brandname()
    {
        System.out.println("Raymonds");
    }
    double billing()
    {
        double total=0.0;
        total=price+price*gst;
        System.out.println("Billing before Discounts");
        return total;
    }
}

class Srinivas extends Clothes
{
    double discount=0.25;
    void color()
    {
        System.out.println("Blue");
    }
    void size()
    {
        System.out.println("Large");
    }
    void brandname()
    {
        System.out.println("Lewis");
    }
    double billing()
    {
        double total=super.billing();
        total=total-total*discount;
        System.out.println("Billing after Discount");
        return total;
    }
    void type()
    {
        System.out.println("Casuals");
    }
}
class Govindraj extends Clothes
{
    double discount=0.3;
    void color()
    {
        System.out.println("GRey");
    }
    void size()
    {
        System.out.println("XL");
    }
    void brandname()
    {
        System.out.println("Peter England");
    }
    double billing()
    {
        double total=super.billing();
        total=total-total*discount;
        System.out.println("Billing after Discount");
        return total;
    }
    void type()
    {
        System.out.println("Formals");
    }
}

class TestClothes
{
    public static void main(String[] args) {
        Clothes c=new Srinivas();
        c.color();
        c.size();
        c.brandname();
        c.billing();
        if(c instanceof Srinivas)
        {
            Srinivas s=(Srinivas)c;
            s.type();
        }
        Clothes c1=new Govindraj();
        c1.color();
        c1.size();
        c1.brandname();
        c1.billing();
        if(c1 instanceof Govindraj)
        {
            Govindraj s1=(Govindraj)c1;
            s1.type();
        }
    }
}
