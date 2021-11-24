package H_单元测试;
/*
当我们需要根据不同的测试条件去进行测试时，这种情况叫做条件测试。
常见的田间测试注解有@Disabled，@EnableOnOs，@DisabledOnOs，@EnabledIfSystemProperty


Junit还提供了一个@Parameterrizedtest,用来进行参数化测试

一种传入测试参数的方法是使用@CsvSource，它的每一个字符串表示一行，一行包含的若干参数用,分隔，因此，上述测试又可以改写如下：
@ParameterizedTest
@CsvSource({ "abc, Abc", "APPLE, Apple", "gooD, Good" })
void testCapitalize(String input, String result) {
    assertEquals(result, StringUtils.capitalize(input));
}
如果有成百上千的测试输入，那么，直接写@CsvSource就很不方便。这个时候，我们可以把测试数据提到一个独立的CSV文件中，然后标注上@CsvFileSource：

@ParameterizedTest
@CsvFileSource(resources = { "/test-capitalize.csv" })
void testCapitalizeUsingCsvFile(String input, String result) {
    assertEquals(result, StringUtils.capitalize(input));
}

使用参数化测试，可以提供一组测试数据，对一个测试方法反复测试。
参数既可以在测试代码中写死，也可以通过@CsvFileSource放到外部的CSV文件中。
 */
public class D_条件测试和参数化测试 {
    public static void main(String[] args) {

    }
}
