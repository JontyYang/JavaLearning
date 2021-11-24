package F_异常处理;

/*
抛出异常时，尽量复用JDK已定义的异常类型；

自定义异常体系时，推荐从RuntimeException派生“根异常”(因为运行时异常不用强制try， catch)，再派生出业务异常；

自定义异常时，应该提供多种构造方法。


 */
public class C_自定义异常 {
    public static void main(String[] args) {
        /*
        Java标准库定义的常用异常包括：
Exception
│
├─ RuntimeException
│  │
│  ├─ NullPointerException
│  │
│  ├─ IndexOutOfBoundsException
│  │
│  ├─ SecurityException
│  │
│  └─ IllegalArgumentException
│     │
│     └─ NumberFormatException
│
├─ IOException
│  │
│  ├─ UnsupportedCharsetException
│  │
│  ├─ FileNotFoundException
│  │
│  └─ SocketException
│
├─ ParseException
│
├─ GeneralSecurityException
│
├─ SQLException
│
└─ TimeoutException
         */
    }
}
