package serverStuff;

import javax.swing.JFrame;

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
	 JFrame frame = new JFrame();
	 test.run(300,500,frame);
	 }
	   public void run(int width, int height, JFrame frame) throws InterruptedException{
	       synchronized(this){
	        	d.run(width, height,frame);
	        for (int i=5; i>0; i--){
	        	System.out.println(i+"second(s) left");
	        sleep(1000);

	        }
	        //System.out.println(d.results.toArray()[0]);
	        	results=d.results.
	        			toArray(dummy);
	       
	        	System.out.println("notify");
	        	notify();
	        
	        }
	   }

}
