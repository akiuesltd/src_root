package com.akieus.lgc.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by aks on 12/02/2016.
 */
public class Misc {
    private static final Logger LOG = getLogger(Misc.class);

    public static final short LONG_SIZE = Long.SIZE / 8;
    public static final short SHORT_SIZE = Short.SIZE / 8;

    public static void sleepSafely(final long duration, final TimeUnit unit) {
        try {
            Thread.sleep(unit.toMillis(duration));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static <T> T ifNullDefault(final T value, final T defaultValue) {
        return value != null ? value : defaultValue;
    }

    public static ThreadFactory namedThreadFactory(String nameFormat) {
        return baseThreadFactoryBuilder().setNameFormat(nameFormat).build();
    }

    private static ThreadFactoryBuilder baseThreadFactoryBuilder() {
        return new ThreadFactoryBuilder().setUncaughtExceptionHandler(uncaughtExceptionHandler());
    }

    private static Thread.UncaughtExceptionHandler uncaughtExceptionHandler() {
        return (Thread t, Throwable e) -> {
            LOG.warn("Uncaught exception in thread=" + t, e);
        };
    }
}
