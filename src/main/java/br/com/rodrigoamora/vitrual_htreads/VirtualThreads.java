package br.com.rodrigoamora.vitrual_htreads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class VirtualThreads {

    private static final Logger logger = LoggerFactory.getLogger(VirtualThreads.class);

    public static String startVirtualThreads() throws InterruptedException, ExecutionException {

        Instant start = Instant.now();
        try(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var task1 = executor.submit(VirtualThreads::executeTask1);
            var task2 = executor.submit(VirtualThreads::executeTask2);
            return task1.get() + task2.get();
        } finally {
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            logger.info("Time elapsed: {} seconds", timeElapsed.getSeconds());
        }

    }

    private static String executeTask1() throws InterruptedException {
        logger.info("task 1");
        Thread.sleep(1000);
        return "task1";
    }

    private static String executeTask2() throws InterruptedException {
        logger.info("task 2");
        Thread.sleep(1000);
        return "task2";
    }

}
