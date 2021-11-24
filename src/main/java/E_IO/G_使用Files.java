package E_IO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class G_使用Files {
    public static void main(String[] args) throws IOException {
        /*
        从Java 7开始，提供了Files和Paths这两个工具类，能极大地方便我们读写文件。
        虽然Files和Paths是java.nio包里面的类，但他俩封装了很多读写文件的简单方法，
        例如，我们要把一个文件的全部内容读取为一个byte[]，可以这么写：
         */
        byte[] data = Files.readAllBytes(Paths.get("D:\\SS\\Idea\\workplace\\JavaLearning\\src\\Z_Common\\files.txt"));
        System.out.println(Arrays.toString(data));

        //如果是文本文件，可以把一个文件的内容全部读取为String,默认使用UTF-8编码
        String string1 = Files.readString(Paths.get("D:\\SS\\Idea\\workplace\\JavaLearning\\src\\Z_Common\\files.txt"));
        System.out.println(string1);
        //指定编码
        String string2 = Files.readString(Paths.get("D:\\SS\\Idea\\workplace\\JavaLearning\\src\\Z_Common\\files.txt"), StandardCharsets.UTF_8);
        System.out.println(string2);
        //按行读取并返回每行内容
        List<String> string3 = Files.readAllLines((Paths.get("D:\\SS\\Idea\\workplace\\JavaLearning\\src\\Z_Common\\files.txt")));
        for (String string : string3) {
            System.out.println(string);
        }

        //写入文件
        Files.write(Paths.get("D:\\SS\\Idea\\workplace\\JavaLearning\\src\\Z_Common\\filesWrite.txt"), data);
        // 写入文本并指定编码:
        Files.writeString(Paths.get("/path/to/file.txt"), "文本内容...", StandardCharsets.ISO_8859_1);
        // 按行写入文本:
        Files.write(Paths.get("/path/to/file.txt"), string3);

        /*
        Files工具类还有copy()、delete()、exists()、move()等快捷方法操作文件和目录。
        最后需要特别注意的是，Files提供的读写方法，受内存限制，只能读写小文件，例如配置文件等，
        不可一次读入几个G的大文件。读写大型文件仍然要使用文件流，每次只读写一部分文件内容。
         */

    }
}
