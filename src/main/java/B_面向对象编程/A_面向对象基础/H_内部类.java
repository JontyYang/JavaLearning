package B_面向对象编程.A_面向对象基础;

public class H_内部类 {
    private String name;

    public H_内部类(String name) {
        this.name = name;
    }

    public H_内部类() {
    }

    //内部类的实例不能单独存在，必须依附于一个外部类存在
    class Inner {
        public void hello() {
            System.out.println("Hello, " + H_内部类.this.name);
        }
    }

    public static void main(String[] args) {
        H_内部类 outer = new H_内部类("Jonty");   //实例化一个outer
        Inner inner = outer.new Inner();  //实例化一个内部类
        inner.hello();

        //匿名类,一般是继承自某个类或者接口，方法只能重写和实现（另一种形式的多态)
        Asych asy = new Asych() {
            @Override
            public void print() {
                System.out.println("我是外部匿名类");
            }
        };
        asy.print();

        Asych asych = new Asych();
        asych.printInfo();
    }
}

class Asych {
    String name;

    public void print() {
        System.out.println("Hello!!!");
    }

    public void printInfo() {
        //匿名类必须只能重写或者实现
        Asych asych = new Asych() {
            public void print() {
                System.out.println("Hello, 内部匿名类！！！" + Asych.this.name);
            }
        };
        asych.print();
    }

}
