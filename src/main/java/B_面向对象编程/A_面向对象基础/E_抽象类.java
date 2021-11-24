package B_面向对象编程.A_面向对象基础;

public class E_抽象类 {
    public static void main(String[] args) {
        Animal animal = new Dog("cat", 16);
        animal.printInfo();
        animal.printName();
    }
}

/**
 * 通过abstract定义的方法是抽象方法，它只有定义，没有实现。抽象方法定义了子类必须实现的接口规范
 * 定义了抽象方法的class必须被定义为抽象类，从抽象类继承的子类必须实现抽象方法
 * 如果不实现抽象方法，则该子类仍是一个抽象类
 * 抽象类中可以定义其他字段和具体方法
 * 抽象类本身不可以被实例化
 */
abstract class Animal {
    protected String name;
    protected int weight;

    public abstract void printInfo();

    public void printName() {
        System.out.println(this.name);
    }

    public Animal() {}
    public Animal(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }
}

class Dog extends Animal {
    @Override
    public void printInfo() {
        System.out.println("Name: " + this.name + ", weight: " + this.weight);
    }

    public Dog(String name, int weight) {
        super(name, weight);
    }

    public Dog() {
    }


}

