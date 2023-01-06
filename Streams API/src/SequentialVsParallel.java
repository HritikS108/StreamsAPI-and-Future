import java.util.List;
import java.util.ArrayList;

// demonstration to illustrate behavior of CPU when we perform long computations in parallel vs sequential stream
// to make CPU busy, doing some arbitrary operations

public class SequentialVsParallel {

	public static int compute(int number)
    {
        int sum=0;
        for (int i = 1; i < 1000000; i++) {
            int div=(number/i);
            sum+=div;
        }
        return sum;
    }
	
	  public static void main(String[] args) {
		  
		  long currentTime=System.currentTimeMillis();
	        List<Integer> data=new ArrayList<Integer>();
	        for (int i = 0; i < 100000; i++) {
	            data.add(i);
	        }
	 
	      long sum = data.stream()
	                .map(i ->(int)Math.sqrt(i))
	                .map(number->compute(number))
	                .reduce(0,Integer::sum);
	 
	        System.out.println(sum);
	        long endTime=System.currentTimeMillis();
	        System.out.println("Time taken to complete:"+(endTime-currentTime)/(1000*60)+" minutes");  // took around 6 minutes
	        
	        // parallel stream takes less time
	        sum=  data.stream()
	                        .parallel()
	                        .map(i ->(int)Math.sqrt(i))
	                        .map(number->compute(number))
	                        .reduce(0,Integer::sum);      
	        
	        System.out.println(sum);
	        endTime=System.currentTimeMillis();
	        System.out.println("Time taken to complete:"+(endTime-currentTime)/(1000*60)+" minutes");  // took around 3 minutes
	        
	  }
}
