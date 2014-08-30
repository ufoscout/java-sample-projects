package ufo.hazelcast.event;
/****************************************************************************\
 __AUTHOR........: Alexey Krylov
 __CREATED.......: 02.03.12
 __VERSION.......: 1.0
 __DESCRIPTION...:
 ****************************************************************************/

import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ufo.hazelcast.BaseTest;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

public class QueueConsistencyTest extends BaseTest {

    /*===========================================[ STATIC VARIABLES ]=============*/

    private static final Logger logger = LoggerFactory.getLogger(QueueConsistencyTest.class);

    /**
     * Max threads that access client and put the objects
     */
    private static final int MAX_THREADS = 250;

    /**
     * Max putted objects (UUID's) per thread
     */
    private static final int COUNT_PER_THREAD = 1000;

    /**
     * Count of queues that we poll per one client
     */
    private static final int TEST_QUEUES_COUNT = 4;

    private static final String PFX_QUEUE = "queue";

    /*===========================================[ INSTANCE VARIABLES ]=========*/

    @Resource
    private HazelcastInstance hazelcast;

    private Map<String, AtomicLong> txCountersMap;
    private AtomicLong txCounter;

    private Map<String, AtomicLong> rxCountersMap;
    private AtomicLong rxCounter;

    private Collection<Throwable> exceptionsDuringPut;

    private ExecutorService pollersExecutor;
    private ExecutorService sendersExecutor;
    private CountDownLatch latch;
    private Timer timer;

    /*===========================================[ CLASS METHODS ]==============*/

    @Test
    public void testConsistency() throws Exception {

        txCountersMap = new ConcurrentHashMap<String, AtomicLong>();
        txCounter = new AtomicLong();

        rxCountersMap = new ConcurrentHashMap<String, AtomicLong>();
        rxCounter = new AtomicLong();

        exceptionsDuringPut = new ConcurrentLinkedQueue<Throwable>();

        pollersExecutor = hazelcast.getExecutorService("hello");
//        this.pollersExecutor = Executors.newFixedThreadPool(TEST_QUEUES_COUNT);
        sendersExecutor = Executors.newFixedThreadPool(MAX_THREADS);

        latch = new CountDownLatch(COUNT_PER_THREAD * MAX_THREADS);

        System.out.println(String.format("Awaiting for tx/rx of [%d] objects", COUNT_PER_THREAD * MAX_THREADS));
        startPrintTimer();
        startQueuePollers();

        // WATCH THIS:
        // 1. Run test without setting -Dhazelcast.executor.client.thread.count - it will fail
        // 2. Run test with setting hazelcast.executor.client.thread.count=1000 - it will pass
        startQueueSenders(
                PFX_QUEUE + 0
/*
        // 3. uncomment this queues and test will pass on any hazelcast.executor.client.thread.count, even for default value
                , PFX_QUEUE + 1,
                PFX_QUEUE + 2,
                PFX_QUEUE + 3
*/
        );

        System.out.println("Senders executor shutdown");
        sendersExecutor.shutdown();

        System.out.println("Await for shutdown");
        sendersExecutor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Await for the latch");
        latch.await(1, TimeUnit.MINUTES);
        System.out.println("Latch done");
        timer.cancel();

        printStat();
        System.out.println("Checking for assertions");
        Assert.assertEquals(0, exceptionsDuringPut.size());
        Assert.assertEquals(rxCounter.get(), txCounter.get());
        Assert.assertEquals(COUNT_PER_THREAD * MAX_THREADS, txCounter.get());
        System.out.println("Congratulations! Test done!");
    }

    private void startPrintTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                QueueConsistencyTest.this.printStat();
            }
        }, TimeUnit.SECONDS.toMillis(1), TimeUnit.SECONDS.toMillis(1));
    }

    private void printStat() {
        System.out.println(String.format("tx/rx: [%d]/[%d]", txCounter.get(), rxCounter.get()));

        for (int i = 0; i < TEST_QUEUES_COUNT; i++) {
            String qName = PFX_QUEUE + i;
            System.out.println(String.format("tx/rx [%s]: [%d]/[%d]", qName, txCountersMap.get(qName).get(), rxCountersMap.get(qName).get()));
        }
    }

    private void startQueuePollers() {
        for (int i = 0; i < TEST_QUEUES_COUNT; i++) {
            final String qName = PFX_QUEUE + i;
            txCountersMap.put(qName, new AtomicLong());
            rxCountersMap.put(qName, new AtomicLong());

            Runnable poller = new Runnable() {
                @Override
                public void run() {
                    IQueue<UUID> queue = hazelcast.getQueue(qName);

                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            queue.take();
                            rxCountersMap.get(qName).incrementAndGet();
                            rxCounter.incrementAndGet();
                            latch.countDown();
                        } catch (InterruptedException e) {
                            logger.info("Take process was interrupted. Possible reason - shutdown is in process", e);
                            break;
                        }
                    }
                }
            };
            pollersExecutor.execute(poller);
        }
    }

    private void startQueueSenders(final String... qNames) {
        final Random random = new Random();

        Runnable generator = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < COUNT_PER_THREAD; i++) {
                    try {
                        String randomQName = qNames[random.nextInt(qNames.length)];
                        IQueue<UUID> queue = hazelcast.getQueue(randomQName);
                        queue.put(UUID.randomUUID());
                        txCountersMap.get(randomQName).incrementAndGet();
                        txCounter.incrementAndGet();
                    } catch (InterruptedException e) {
                        logger.info("Put process was interrupted. Possible reason - shutdown is in process", e);
                        break;
                    } catch (Exception e) {
                        exceptionsDuringPut.add(e);
                    }
                }
            }
        };

        for (int i = 0; i < MAX_THREADS; i++) {
            sendersExecutor.execute(generator);
        }
    }
}