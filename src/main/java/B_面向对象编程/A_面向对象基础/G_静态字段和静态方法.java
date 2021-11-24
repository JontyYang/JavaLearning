package B_面向对象编程.A_面向对象基础;

public class G_静态字段和静态方法 {
    public static void main(String[] args) {
        /**
         * 实例字段在每个实例中都有自己的一个独立“空间”，但是静态字段只有一个共享“空间”，所有实例都会共享该字段
         * 推荐用类名来访问静态字段。可以把静态字段理解为描述class本身的字段
         *
         * static修饰的方法称为静态方法
         *调用静态方法则不需要实例变量，通过类名就可以调用
         * 因为静态方法属于class而不属于实例，因此，静态方法内部，无法访问this变量，也无法访问实例字段，它只能访问静态字段
         */
    }
}

interface Person2 {
//    interface是一个纯抽象类，所以它不能定义实例字段。但是，interface是可以有静态字段的，并且静态字段必须为final类型
    public static final int MALE = 1;
    public static final int FEMALE = 2;
    //因为interface的字段只能是public static final类型，所以我们可以把这些修饰符都去掉，上述代码可以简写为
    // 编译器会自动加上public statc final:
//    int MALE = 1;
//    int FEMALE = 2;
}
