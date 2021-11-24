package E_IO;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Java标准库的java.io.File对象表示一个文件或者目录：
 *
 * 创建File对象本身不涉及IO操作；
 * 可以获取路径／绝对路径／规范路径：getPath()/getAbsolutePath()/getCanonicalPath()；
 * 可以获取目录的文件和子目录：list()/listFiles()；
 * 可以创建/删除/遍历文件和目录。
 */
public class A_File对象 {
    public static void main(String[] args) {
        //打印当前平台的文件分隔符
        System.out.println(File.separator);
        /**
         * File对象既可以表示一个文件也可以表示一个目录
         */
        //构造File对象，该操作并不会导致任何磁盘操作，只有当调用File的一些方法是才会进行磁盘操作。
        File file1 = new File("D:\\SS\\Idea\\workplace\\JavaLearning\\src\\Z_Common\\IO.txt");
        File file2 = new File("../Commmon");
        System.out.println(file1.getPath());  //相对路径
        System.out.println(file1.getAbsolutePath());  // 绝对路径
        try {
            System.out.println(file1.getCanonicalPath());  //规范路径
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file1.isFile());
        System.out.println(file2.isDirectory());

        /**
         * 用File对象获取到一个文件时，还可以进一步判断文件的权限和大小：
         *
         * boolean canRead()：是否可读；
         * boolean canWrite()：是否可写；
         * boolean canExecute()：是否可执行；
         * long length()：文件字节大小。
         * 对目录而言，是否可执行表示能否列出它包含的文件和子目录。
         */
        try {
            if (file1.createNewFile()) {
                System.out.println("创建成功！！！");
                if (file1.delete()) {
                    System.out.println("删除成功！！！");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file1.canExecute());

        //有些时候，程序需要读写一些临时文件，File对象提供了createTempFile()来创建一个临时文件，以及deleteOnExit()在JVM退出时自动删除该文件。
        File f = null; // 提供临时文件的前缀和后缀
        try {
            //如果第三个参数没有，默认添加在某一个目录
            f = File.createTempFile("tmp-", ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        f.deleteOnExit(); // JVM退出时自动删除
        System.out.println(f.isFile());
        System.out.println(f.getAbsolutePath());

        //遍历文件和目录
        File file3 = new File("D:\\SS\\Idea\\workplace\\JavaLearning\\src\\B_面向对象编程");
        //仅仅列出该目录下的文件对象（不显示更下一级的）
        File[] files = file3.listFiles();
        System.out.println(Arrays.toString(files));
        File[] files1 = files[0].listFiles();
        System.out.println(Arrays.toString(files1));
        //列出该目录下的文件名以字符串形式
        String[] string = file3.list();
        System.out.println(Arrays.toString(string));
        System.out.println(files1[0]);
        //
        /**
         * 和文件操作类似，File对象如果表示一个目录，可以通过以下方法创建和删除目录：
         *
         * boolean mkdir()：创建当前File对象表示的目录；
         * boolean mkdirs()：创建当前File对象表示的目录，并在必要时将不存在的父目录也创建出来；
         * boolean delete()：删除当前File对象表示的目录，当前目录必须为空才能删除成功。
         */
        //引用对象如果为null，会出现空指针异常
        //files1 = null;
        for (File file : files1) {
            System.out.println(file);
        }


    }
}
