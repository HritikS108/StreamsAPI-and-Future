import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// implementation of callable and Future using executor service
// We are using Executor framework to execute 100 tasks in parallel and use Java Future 
// to get the result of the submitted tasks.


class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        //return the thread name executing this callable task
        return Thread.currentThread().getName();
    }
}
    
public class Example2 {

	 public static void main(String[] args) {

		        ExecutorService executor = Executors.newFixedThreadPool(10);
		        List<Future<String>> list = new ArrayList<Future<String>>();
		        Callable<String> callable = new MyCallable();
		        
		        for(int i=0; i< 100; i++){
		            //submit Callable tasks to be executed by thread pool
		            Future<String> future = executor.submit(callable);
		            //add Future to the list, we can get return value using Future
		            list.add(future);
		        }
		        for(Future<String> futureTask : list){
		            try {
		                System.out.println(new Date()+ "::"+futureTask.get());
		            } catch (InterruptedException | ExecutionException e) {
		                e.printStackTrace();
		            }
		        }
		        
		        executor.shutdown(); 
		    }

		 
	 }



