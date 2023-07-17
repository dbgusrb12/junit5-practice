package com.hg.junit5practice.junit5;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final long THRESHOLD = 1000L;

    private boolean flag = true;

    private final long threshold;

    public FindSlowTestExtension() {
        this.threshold = THRESHOLD;
    }

    public FindSlowTestExtension(long threshold) {
        this.threshold = threshold;
        this.flag = false;
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        String testClassName = context.getRequiredTestClass().getName();
        String testMethodName = context.getRequiredTestMethod().getName();
        Store store = context.getStore(Namespace.create(testClassName, testMethodName));
        store.put("START_TIME", System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        String testClassName = context.getRequiredTestClass().getName();
        String testMethodName = context.getRequiredTestMethod().getName();
        Store store = context.getStore(Namespace.create(testClassName, testMethodName));
        Long start_time = store.remove("START_TIME", long.class);
        long duration = System.currentTimeMillis() - start_time;
        long threshold = flag ? THRESHOLD : this.threshold;
        if (duration > threshold) {
            System.out.printf("test running time is greater than %d millis", threshold);
        }
    }
}
