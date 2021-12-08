package F_异常处理;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class G_Logback {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(G_Logback.class);
        logger.debug("sss");
        logger.info("sjkl ");
    }
}
