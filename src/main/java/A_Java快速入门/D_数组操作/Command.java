package A_Java快速入门.D_数组操作;

/**
 * java程序入口方法是main，VM接收用户输入并将输入传给main方法（该输入是一个命令行参数）
 * 它是一个String[]数组
 */
public class Command {
    public static void main(String[] args) {
        for (String n : args) {
            System.out.println(n);
        }
    }
}
