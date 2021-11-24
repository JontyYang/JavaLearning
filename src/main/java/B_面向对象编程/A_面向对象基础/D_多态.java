package B_面向对象编程.A_面向对象基础;

//多态具有一个非常强大的功能，就是允许添加更多类型的子类实现功能扩展，却不需要修改基于父类的代码
//利用上转型(实例上转型以及方法参数上转型)，进行多态（方法重写）

import java.util.Objects;

/**
 * 如果一个类或者类的方法不想被继承或者子类修改，可以使用final字段
 * 如果一个类的字段(如姓名)设置为final字段，在构造函数中初始化它，
 * 可以保证实例一旦创建就不可以修改
 *
 * 子类如果想调用子类方法，则使用super. 进行调用
 */
public class D_多态 {
    double total = 0;

    public double getTotalTax(InCome[] inComes) {
        for (InCome inCome : inComes) {
            total += inCome.getTax();
        }
        return total;
    }

    public static void main(String[] args) {
        D_多态 Poly = new D_多态();

        InCome[] inCome = new InCome[] {
                new InCome(3000),
                new Salary(7500),
                new StateCouncilSpecialAllowance(15000)
        };

        Salary salary = new Salary(3000);
        salary.hashCode();
        InCome inCome1 = new InCome(3000);
        System.out.println(Poly.getTotalTax(inCome));

        //计算一个实例的哈希值
        System.out.println(inCome[0].hashCode());

        //判断两个实例是否逻辑相等（同类型且值相等）
        System.out.println(inCome[0].equals(inCome1));

        //把一个实例输出为string
        System.out.println(salary.toString());
    }

}

class InCome {
    protected double inCome;

    public InCome(double inCome) {
        this.inCome = inCome;
    }

    public double getTax() {
        return inCome * 0.1;
    }

    /**
     * 因为所有的类都继承自Object，因此可以修改其几个重要的方法
     * toString()：把instance输出为String；
     * equals()：判断两个instance是否逻辑相等；
     * hashCode()：计算一个instance的哈希值
     */
    @Override
    public boolean equals(Object o) {
        //当且仅当o为Person类型
        if (o instanceof InCome) {
            //并且inCome字段相同时，返回true
            InCome p = (InCome) o;
            return this.inCome == p.inCome;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inCome);
        //return this.inCome.hashCode();
    }

    @Override
    public String toString() {
        return "InCome{" +
                "inCome=" + inCome +
                '}';
    }
}

class Salary extends InCome{
    public Salary(double inCome) {
        super(inCome);
    }

    @Override
    public double getTax() {
        if (this.inCome <= 5000) {
            return 0;
        }
        return (this.inCome - 5000) * 0.2;
    }



}

class StateCouncilSpecialAllowance extends InCome{
    public StateCouncilSpecialAllowance(double inCome) {
        super(inCome);
    }

    @Override
    public double getTax() {
        return 0;
    }
}