package E_IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * InputStream就是Java标准库提供的最基本的输入流,以字节读取输入流。
 * InputStream并不是一个接口，而是一个抽象类，它是所有输入流的超类。这个抽象类定义的一个最重要的方法就是int read()。
 *
 * Java标准库的java.io.InputStream定义了所有输入流的超类：
 *
 * FileInputStream实现了文件流输入；
 *
 * ByteArrayInputStream在内存中模拟一个字节流输入。
 *
 * 总是使用try(resource)来保证InputStream正确关闭。
 */
public class B_InputStrean {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = null;
        //在读取文件的3过程中，会发生一些错误（IOException）
        try {
            //Input（OutputStream）Stream默认以ASCALL编码读取字节流，因此中文会有乱码
            inputStream = new FileInputStream("D:\\SS\\Idea\\workplace\\JavaLearning\\src\\Z_Common\\Read_Write.txt");
            int n;
            //InputStream
            while ((n = inputStream.read()) != -1) {
                System.out.println((char)n);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
        /**
         * 在读取流的时候，一次读取一个字节并不是最高效的方法。很多流支持一次性读取多个字节到缓冲区，对于文件和网络流来说，
         * 利用缓冲区一次性读取多个字节效率往往要高很多。InputStream提供了两个重载方法来支持读取多个字节：
         *
         * int read(byte[] b)：读取若干字节并填充到byte[]数组，返回读取的字节数
         * int read(byte[] b, int off, int len)：指定byte[]数组的偏移量和最大填充数
         * 利用上述方法一次读取多个字节时，需要先定义一个byte[]数组作为缓冲区，read()方法会尽可能多地读取字节到缓冲区，
         * 但不会超过缓冲区的大小。read()方法的返回值不再是字节的int值，而是返回实际读取了多少个字节。如果返回-1，表示没有更多的数据了。
         */
        // 定义4个字节大小的缓冲区:
        byte[] buffer = new byte[4];
        int n;
        inputStream = new FileInputStream("D:\\SS\\Idea\\workplace\\JavaLearning\\src\\Z_Common\\Read_Write.txt");
        while ((n = inputStream.read(buffer)) != -1) { // 读取到缓冲区
            System.out.println("read " + n + " bytes.");
        }
        inputStream.close();

        //对于文件流而言，他是阻塞的，只有他执行完才会执行下一条语句。
    }
}
