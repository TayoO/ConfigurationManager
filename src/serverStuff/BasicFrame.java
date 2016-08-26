package serverStuff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class BasicFrame extends JFrame implements ActionListener{


			  // Create a constructor method
			  public BasicFrame(){
			    // All we do is call JFrame's constructor.
			    // We don't need anything special for this
			    // program.
			    super();
			  }

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("action event");
				// TODO Auto-generated method stub
				
			}
}

