package serverStuff;

public class DropDownListThread extends Thread {
	 DropDownList d;
	 String [] dummy={"dummy"};
	 String [] results;
	 public DropDownListThread(String [] info){	 
	 d=new DropDownList(info);
	 }
	 public DropDownListThread(){
		 String[] tes= {"err","is", "human"};
		 d=new DropDownList(tes);
		 }
	 public static void main (String [] args) throws InterruptedException
	 {
	 DropDownListThread test=new DropDownListThread();
	 test.run(300,500);
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
