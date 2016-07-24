package countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable{
	private CountDownLatch latch;
	Processor(CountDownLatch latch){
		this.latch = latch;
	}
	@Override
	public void run() {
		System.out.println("Thread Started");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread Finished");
		latch.countDown();
	}	
}
public class UseCountdownLatch {
	public static void main(String [] args){
		CountDownLatch latch = new CountDownLatch(3);
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		for(int i=0; i<3; i++){
			threadPool.submit(new Processor(latch));
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Main Threads completed");
	}
}
