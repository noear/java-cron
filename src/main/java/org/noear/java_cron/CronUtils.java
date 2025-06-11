/*
 * Copyright noear.org and authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
 * @since 1.0
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
                    expr = build0(cron);
                    cached.put(cron, expr);
                }
            }
        }

        return expr;
    }

    private static CronExpressionPlus build0(String cron) throws ParseException {
        int tzIdx = cron.lastIndexOf(' ');
        String tzTmp = cron.substring(tzIdx + 1);

        CronExpressionPlus expr;
        if (tzTmp.startsWith("-") || tzTmp.startsWith("+")) {
            cron = cron.substring(0, tzIdx);

            expr = new CronExpressionPlus(cron);
            expr.setTimeZone(TimeZone.getTimeZone(ZoneId.of(tzTmp)));
        } else {
            expr = new CronExpressionPlus(cron);
        }

        return expr;
    }
}
