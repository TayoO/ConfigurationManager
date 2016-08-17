package serverStuff;

public class DropDownListThread extends Thread {
	 DropDownList d;
	 String [] results;
	 public DropDownListThread(String [] info){	 
	 d=new DropDownList(info);
	 }
	 
	   public void run(int width, int height) throws InterruptedException{
	        synchronized(this){
	        	d.run(width, height);
	        while (!d.done){
	        sleep(1000);
	        System.out.println("not done");
	        }
	        //System.out.println(d.results.toArray()[0]);
	        	//results=d.results.toArray(results);
	        	System.out.println("notify");
	        	notify();
	        
	        }
	   }

}
