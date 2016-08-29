package serverStuff;

public class DropDownListThread extends Thread {
	 DropDownList d;
	 String [] dummy;
	 String [] results;
	 public DropDownListThread(String [] info){	 
	 d=new DropDownList(info);
	 }
	 public static void main (String [] args)
	 {
		 DropDownListThread({"err","is", "human"});
	 }
	   public void run(int width, int height) throws InterruptedException{
	        synchronized(this){
	        	d.run(width, height);
	        while (!d.done){
	        	System.out.println("sleeping half a second");
	        sleep(500);

	        }
	        //System.out.println(d.results.toArray()[0]);
	        	results=d.results.
	        			toArray(dummy);
	       
	        	System.out.println("notify");
	        	notify();
	        
	        }
	   }

}
