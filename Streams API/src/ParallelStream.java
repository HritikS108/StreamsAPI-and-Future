import java.util.List;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class ParallelStream {

	
	 public static void main(String[] args) {
		 
		 ArrayList<Integer> arr = new ArrayList<Integer>();
		 
		 Scanner sc = new Scanner(System.in);
		 int totalElems = sc.nextInt();
		 
		 for(int i=0;i<totalElems;i++) {
			 int x = sc.nextInt();
			 arr.add(x);
		 }
		 
		 // finding sum of all elements using sequential stream with some initial value
		 int sum = arr.stream().reduce(10,(ans,x)->ans+x);
		 System.out.println(" sum using sequential stream : " + sum);
		 
		 // finding sum using parallel stream with some initial value
         sum = arr.stream().parallel().reduce(10, (ans,x)-> ans+x);
         System.out.println(" sum using parallel stream : " + sum);   // 5 gets added the no of times equal to worker threads
		 // one solution of it is adding 5 later
         
         // sorting elements using parallel stream 
         arr.parallelStream().sorted().forEach(x->System.out.print(x + " "));
         // so basically we should not use parallel stream when results need to be ordered.
         
         
         // reading from a file :- output will be very different and random
         File f = new File("C:\\Users\\91978\\Downloads\\demo.txt");  
        
         try {
        	 List<String> text = Files.readAllLines(f.toPath());  
        	 text.parallelStream().forEach(System.out::println);  
         }
         catch(Exception e) {
        	 System.out.println("exception occured : " + e);
         }
         
	 }
}
