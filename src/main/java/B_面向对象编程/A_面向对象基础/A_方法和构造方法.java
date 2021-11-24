package B_面向对象编程.A_面向对象基础;

public class A_方法和构造方法 {
    public static void main(String[] args) {
        Person person1 = new Person("小明", 18);
        System.out.printf("Hi, %s, you are %d.\n", person1.getName(), person1.getAge());
        Person person2 = new Person("Jonty");
        System.out.println("Hi, " + person2.getName() + ", you are " + person2.getAge() + ".");
        Person person3 = new Person();
        System.out.println("Hi, " + person3.getName() + ", you are " + person3.getAge() + ".");
        System.out.println(person1.toString());

    }
}

/**
 * 参数绑定，对于基本数据类型将调用方值复制给接收方参数，对于引用类型是将调用方值（引用地址）复制给接收方参数。
 * 因此有时修改引用类型的值时，会同时修改接收方参数值
 */
class Person {
    private String name;
    private int age;

    /**
     * 构造方法，如果没有写构造方法，编译器会为我们自动生成一个没有参数，没有执行语句的默认构造函数
     * 如果写了构造方法，建议补充默认构造函数
     * @return 无返回值
     */
    public Person(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    //多构造方法，构造方法可以调用其它构造方法,使用this()进行调用
    public Person(String name) {
        this(name, 18);    //调用另一个构造方法Person(String, int)
    }

    public Person() {
        this("Unamed");   //调用另一个构造方法Person(String)
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        //姓名不允许传入空字符和null
        if (name == null | name == "") {
            throw new IllegalArgumentException("invalid name");
        }
        //去除字符串收尾空格
        this.name = name.strip();
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }
}