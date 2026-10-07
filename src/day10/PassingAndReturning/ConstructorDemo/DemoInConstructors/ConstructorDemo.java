package day10.PassingAndReturning.ConstructorDemo.DemoInConstructors;

class GrandFather {

    public GrandFather(int a) {
        System.out.println("GrandFather Class Constructor");
    }
}

class Father extends GrandFather {

    Father() {
        super(10);
        System.out.println("Father class Constructor");
    }
}

class Son extends Father {

    Son() {
        System.out.println("Son class Constructor");
    }
}

class TestMain {

    public static void main(String[] args) {

        GrandFather s = new Son();

    }
}