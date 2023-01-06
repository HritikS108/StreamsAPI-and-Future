import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

// The parallelism in ForkJoinPool is not the maximum number of threads in the pool. 
// It is a target of active threads. If some threads are blocked, the pool may start new 
// threads to achieve the desired level of parallelism.
public class CustomThreadPool {

	   public static void main(String[] args) throws InterruptedException, ExecutionException {
		  
		    ArrayList<Long> aList = new ArrayList<Long>();
		    
		    for(long i=1;i<=1000000;i++) {
		    	aList.add(i);
		    }

		    ForkJoinPool customThreadPool = new ForkJoinPool(4);
		    long actualTotal = customThreadPool.submit(
		      () -> aList.parallelStream().reduce(0L, Long::sum)).get();
		 
		    System.out.println((1000000 + 1) * 1000000/ 2 == actualTotal);
	   }
}
