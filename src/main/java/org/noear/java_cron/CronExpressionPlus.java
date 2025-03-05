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
import java.util.Collections;
import java.util.Set;

/**
 * CronExpression 加强版
 *
 * @author noear
 * @since 1.0
 */
public class CronExpressionPlus extends CronExpression {
    public CronExpressionPlus(String cronExpression) throws ParseException {
        super(cronExpression);
    }

    /**
     * Constructs a new {@code CronExpression} as a copy of an existing
     * instance.
     *
     * @param expression The existing cron expression to be copied
     */
    public CronExpressionPlus(CronExpression expression) {
        super(expression);
    }

    public Set<Integer> getSeconds() {
        return Collections.unmodifiableSet(seconds);
    }

    public Set<Integer> getMinutes() {
        return Collections.unmodifiableSet(minutes);
    }

    public Set<Integer> getHours() {
        return Collections.unmodifiableSet(hours);
    }

    public Set<Integer> getDaysOfMonth() {
        return Collections.unmodifiableSet(daysOfMonth);
    }

    public Set<Integer> getMonths() {
        return Collections.unmodifiableSet(months);
    }

    public Set<Integer> getDaysOfWeek() {
        return Collections.unmodifiableSet(daysOfWeek);
    }

    public Set<Integer> getYears() {
        return Collections.unmodifiableSet(years);
    }
}
