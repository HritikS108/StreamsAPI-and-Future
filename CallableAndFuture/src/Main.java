import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

//We need a callable object to create a future task and then we can use Java Thread Pool Executor 
//to process these asynchronously.

class CallableExample implements Callable
{
  
  public Object call() throws Exception
  {
    Integer randomNum = new Random().nextInt(10);
  
    Thread.sleep(randomNum * 100);
  
    return randomNum;
  }
  
}
  
public class Main
{
  public static void main(String[] args) throws Exception
  {

    FutureTask[] randomNumTasks = new FutureTask[10];
  
    for (int i = 0; i < 10; i++)
    {
      Callable task1 = new CallableExample();
  
      // Create the FutureTask(it implements both future and runnable interface) with Callable
      randomNumTasks[i] = new FutureTask(task1);
  
      Thread t = new Thread(randomNumTasks[i]);
      t.start();
    }
  // All interaction with the thread after it starts is using the FutureTask object as it 
 //  implements the Future interface
    for (int i = 0; i < 10; i++)
    {
      System.out.println(randomNumTasks[i].get());
    }
  }
}