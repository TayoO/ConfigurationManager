package serverStuff;

public class DropDownListThread extends Thread {
	 DropDownList d;
	 String [] dummy={"hey","4"};
	 String [] results;
	 public DropDownListThread(String [] info){	 
	 d=new DropDownList(info);
	 }
	 
	   public void run(int width, int height) throws InterruptedException{
	        synchronized(this){
	        	d.run(width, height);
	        while (!d.done){
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
