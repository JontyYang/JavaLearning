package E_IO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * OutputStream是Java标准库提供的最基本的输出流。
 *
 * 和InputStream类似，OutputStream也是抽象类，它是所有输出流的超类。
 * 这个抽象类定义的一个最重要的方法就是void write(int b),该方法会议ASCAll码编码形式写入一个字节
 *
 * OutputStream还提供了一个flush()方法，它的目的是将缓冲区的内容真正输出到目的地。
 *
 * 为什么要有flush()？因为向磁盘、网络写入数据的时候，出于效率的考虑，操作系统并不是输出一个字节就立刻写入到文件或者发送到网络，
 * 而是把输出的字节先放到内存的一个缓冲区里（本质上就是一个byte[]数组），等到缓冲区写满了，再一次性写入文件或者网络。对于很多IO设备来说，
 * 一次写一个字节和一次写1000个字节，花费的时间几乎是完全一样的，所以OutputStream有个flush()方法，能强制把缓冲区内容输出。
 *
 * 通常情况下，我们不需要调用这个flush()方法，因为缓冲区写满了OutputStream会自动调用它，并且，在调用close()方法关闭OutputStream之前，也会自动调用flush()方法。
 *
 * 但是，在某些情况下，我们必须手动调用flush()方法。举个栗子：
 *
 * 小明正在开发一款在线聊天软件，当用户输入一句话后，就通过OutputStream的write()方法写入网络流。小明测试的时候发现，发送方输入后，接收方根本收不到任何信息，怎么肥四？
 *
 * 原因就在于写入网络流是先写入内存缓冲区，等缓冲区满了才会一次性发送到网络。如果缓冲区大小是4K，则发送方要敲几千个字符后，
 * 操作系统才会把缓冲区的内容发送出去，这个时候，接收方会一次性收到大量消息。
 *
 * 解决办法就是每输入一句话后，立刻调用flush()，不管当前缓冲区是否已满，强迫操作系统把缓冲区的内容立刻发送出去。
 *
 * 实际上，InputStream也有缓冲区。例如，从FileInputStream读取一个字节时，操作系统往往会一次性读取若干字节到缓冲区，并维护一个指针指向未读的缓冲区。然后，
 * 每次我们调用int read()读取下一个字节时，可以直接返回缓冲区的下一个字节，避免每次读一个字节都导致IO操作。当缓冲区全部读完后继续调用read()，
 * 则会触发操作系统的下一次读取并再次填满缓冲区。
 */
public class C_OutputStream {
    public static void main(String[] args) throws IOException {
        OutputStream outputStream = new FileOutputStream("D:\\SS\\Idea\\workplace\\JavaLearning\\src\\Z_Common\\Read_Write.txt");
        //如果文件中有内容会重写，以ASCALL编码读取字节
        outputStream.write(72);  //H
        outputStream.write(101); //e
        //刷新缓冲区
        outputStream.flush();
        outputStream.close();

        /**
         * InputStream一样，上述代码没有考虑到在发生异常的情况下如何正确地关闭资源。写入过程也会经常发生IO错误，
         * 例如，磁盘已满，无权限写入等等。我们需要用try(resource)来保证OutputStream在无论是否发生IO错误的时候都能够正确地关闭
         */
        try {
            outputStream = new FileOutputStream("D:\\SS\\Idea\\workplace\\JavaLearning\\src\\Z_Common\\Read_Write.txt");
            //每次写入一个字节非常麻烦，更常见的方法是一次性写入若干字节。这时，可以用OutputStream提供的重载方法void write(byte[])来实现
            outputStream.write("hello".getBytes());
        } finally {
            outputStream.close();
        }

        /**
         * Java标准库的java.io.OutputStream定义了所有输出流的超类：
         *
         * FileOutputStream实现了文件流输出；
         *
         * ByteArrayOutputStream在内存中模拟一个字节流输出。
         *
         * 某些情况下需要手动调用OutputStream的flush()方法来强制输出缓冲区。
         *
         * 总是使用try(resource)来保证OutputStream正确关闭。
         */


        /**
         * Java的IO标准库使用Filter模式为InputStream和OutputStream增加功能：
         *
         * 可以把一个InputStream和任意个FilterInputStream组合；
         *
         * 可以把一个OutputStream和任意个FilterOutputStream组合。
         *
         * Filter模式可以在运行期动态增加功能（又称Decorator模式）
         */
        //当我们需要给一个“基础”InputStream附加各种功能时，我们先确定这个能提供数据源的InputStream，
        // 因为我们需要的数据总得来自某个地方，例如，FileInputStream，数据来源自文件：
        //InputStream file = new FileInputStream("test.gz");
        //紧接着，我们希望FileInputStream能提供缓冲的功能来提高读取的效率，因此我们用BufferedInputStream
        // 包装这个InputStream，得到的包装类型是BufferedInputStream，但它仍然被视为一个InputStream：
        //InputStream buffered = new BufferedInputStream(file);
        //最后，假设该文件已经用gzip压缩了，我们希望直接读取解压缩的内容，就可以再包装一个GZIPInputStream：
        //InputStream gzip = new GZIPInputStream(buffered);



    }
}
