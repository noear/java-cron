package org.noear.java_cron;

import java.text.ParseException;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Cron 工具类
 *
 * @author noear
 * @since 1.1
 * */
public class CronUtils {
    private static final Map<String, CronExpressionPlus> cached = new HashMap<>();

    /**
     * 获取表达式
     *
     * @param cron 支持：0 * * * * ? * 或 0 * * * * ? * +80
     */
    public static CronExpressionPlus get(String cron) {
        try {
            return get0(cron);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Cron parsing failed: " + cron, e);
        }
    }

    /**
     * 获取下个时间点
     */
    public static Date getNextTime(String cron, Date baseTime) throws ParseException {
        return get(cron).getNextValidTimeAfter(baseTime);
    }

    /**
     * 验证表达式有效性
     */
    public static boolean isValid(String cron) {
        try {
            return get0(cron) != null;
        } catch (ParseException e) {
            return false;
        }
    }

    private static CronExpressionPlus get0(String cron) throws ParseException {
        CronExpressionPlus expr = cached.get(cron);

        if (expr == null) {
            synchronized (cached) {
                expr = cached.get(cron);

                if (expr == null) {
                    int tzIdx = cron.indexOf("+");
                    if (tzIdx < 0) {
                        tzIdx = cron.indexOf("-");
                    }

                    if (tzIdx > 0) {
                        String tz = cron.substring(tzIdx);
                        cron = cron.substring(0, tzIdx - 1);

                        expr = new CronExpressionPlus(cron);
                        expr.setTimeZone(TimeZone.getTimeZone(ZoneId.of(tz)));
                    } else {
                        expr = new CronExpressionPlus(cron);
                    }

                    cached.put(cron, expr);
                }
            }
        }

        return expr;
    }
}
