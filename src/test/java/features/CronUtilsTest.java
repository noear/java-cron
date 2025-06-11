package features;

import org.junit.jupiter.api.Test;
import org.noear.java_cron.CronExpression;
import org.noear.java_cron.CronUtils;

/**
 * @author noear 2025/6/11 created
 */
public class CronUtilsTest {
    @Test
    public void case1() throws Exception {
        new CronExpression("0 0 10-20 * * ?");
        CronUtils.isValid("0 0 10-20 * * ?");
        CronUtils.get("0 0 10-20 * * ?");
    }

    @Test
    public void case2() throws Exception {
        CronUtils.get("0 0 10-20 * * ? -08");
        CronUtils.get("0 0 10-20 * * ? +08");
    }
}
