import java.awt.Graphics;

import javax.swing.JFrame;

public class BasicFrame extends JFrame{

			  // Create a constructor method
			  public BasicFrame(){
			    // All we do is call JFrame's constructor.
			    // We don't need anything special for this
			    // program.
			    super();
			  }

			  // The following methods are instance methods.
			  /* Create a paint() method to override the one in JFrame.
			     This is where the drawing happens. 
			     We don't have to call it in our program, it gets called
			     automatically whenever the frame needs to be redrawn,
			     like when it it made visible or moved or whatever.*/
			  public void paint(Graphics g){
			    g.drawRect(40,40,150,50);
			    g.drawRect(40,120,150,50);
			    g.drawRect(40,200,150,50);// Draw a line from (10,10) to (150,150)
			  }
			  public static void main(String arg[]){
				    // create an identifier named 'window' and
				    // apply it to a new BasicFrame object
				    // created using our constructor, above.
				    BasicFrame frame = new BasicFrame();

				    // Use the setSize method that our BasicFrame
				    // object inherited to make the frame
				    // 200 pixels wide and high.
				    frame.setSize(300,300);

				    // Make the window show on the screen.
				    frame.setVisible(true);
				  }
				
}
