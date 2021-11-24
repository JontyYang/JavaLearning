package B_面向对象编程.A_面向对象基础;

public class F_接口 {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.print();
    }
}

//接口方法默认是public abstract
//接口和抽象类，一般使用接口，因为接口更加抽象
//接口只有抽象方法和default方法
interface Interface {
    public void run();

    //default方法并不是每个每个类都要实现，如果某个类需要该方法，重写实现就可，继承该接口的类默认是继承该方法的
    //JdK>=1.8
    default void print() {
        System.out.println("Hello!!!");
    }
}

class Cat implements Interface {
    @Override
    public void run() {
        System.out.println("Run Run Run!!!");
    }
}

