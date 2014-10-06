package ufo.servlet3;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AsynchExecutor {

	private static final Executor executor = Executors.newFixedThreadPool(10);

	public static void addJob(Job job) {
		System.out.println("Added job");
		executor.execute(new Runnable(){
            @Override
			public void run() {
            	System.out.println("Executing job");
                job.execute();
            }
        });
	}

}
