package F_异常处理;

/*
如果遇到NullPointerException，我们应该如何处理？首先，必须明确，NullPointerException是一种代码逻辑错误，
遇到NullPointerException，遵循原则是早暴露，早修复，严禁使用catch来隐藏这种编码错误：
 */
public class D_NullPointerException {
    public static void main(String[] args) {
        /*
        使用空字符串""而不是默认的null可避免很多NullPointerException，编写业务逻辑时，用空字符串""表示未填写比null安全得多。

        NullPointerException是Java代码常见的逻辑错误，应当早暴露，早修复；
         */
    }
}
