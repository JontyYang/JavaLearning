package B_面向对象编程.A_面向对象基础;

public class C_继承 {
    public static void main(String[] args) {
        Student1 student = new Student1("Jonty", 18, 100);
        System.out.println(student);
        //上转型对象（父类对象)只能访问父类的属性，若要访问子类属性，还需转为子类；
        Person1 person = student;
        ((Student1) person).getScore();
        person.getAge();
        //instanceOf判断实例是否属于某个类型,java14才支持
    }
}

/**
 * 参数绑定，对于基本数据类型将调用方值复制给接收方参数，对于引用类型是将调用方值（引用地址）复制给接收方参数。
 * 因此有时修改引用类型的值时，会同时修改接收方参数值
 */
class Person1 {
    protected String name;
    private int age;

    /**
     * 构造方法，如果没有写构造方法，编译器会为我们自动生成一个没有参数，没有执行语句的默认构造函数
     * 如果写了构造方法，建议补充默认构造函数
     * @return 无返回值
     */
    public Person1(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    //多构造方法，构造方法可以调用其它构造方法,使用this()进行调用
    public Person1(String name) {
        this(name, 18);    //调用另一个构造方法Person1(String, int)
    }

    public Person1() {
        this("Unamed");   //调用另一个构造方法Person1(String)
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

    @Override
    public String toString() {
        return "Person1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

//如果该类继承了父类的私有属性，则子类访问不了该属性，保证了类的封闭性

/**
 * 继承有个特点，就是子类无法访问父类的private字段或者private方法
 * 为了让子类可以访问父类的字段，我们需要把private改为protected
 * 用protected修饰的字段可以被子类访问,protected关键字可以把字段和方法的访问权限控制在继承树内部，
 * 一个protected字段和方法可以被其子类，以及子类的子类以及更低层的子类所访问,
 *
 *
 */
class Student1 extends Person1 {
    private int score;

    public Student1(String name, int age, int score) {
        //可以使用super调用父类非私有字段或者方法
        //子类并不会继承父类的任何构造方法
        //因此使用super()调用子类构造方法
        //如果没用super()，则自动生成一个父类默认构造方法去初始化，
        // 若父类刚好没定义默认初始化构造函数，只定义了其他构造函数，则会报错
        super(name, age);
        this.setScore(score);
    }

    public Student1(String name, int score) {
        super(name);
        this.setScore(score);
    }

    public Student1(int score) {
        this.setScore(score);
    }

    public Student1() {}

    public int getScore() {
        return score;
    }

    private void setScore(int score) {
        this.score = score;
    }

    //父类方法重写
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return super.getAge();
    }

    @Override
    public String toString() {
        return "Student1{" +
                "name=" + this.getName() + '\'' +
                ", age=" + this.getAge() +'\'' +
                ", score=" + this.getScore() +
                '}';
    }
}


