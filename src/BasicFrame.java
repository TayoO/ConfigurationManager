import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class BasicFrame extends JFrame implements ActionListener{
	JButton b1;
	JButton b2;
	JButton b3;

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
				  
				  //b1.setV
				  /*
			    g.drawRect(40,40,150,50);
			    g.drawRect(40,120,150,50);
			    g.drawRect(40,200,150,50);
			    g.drawString( "changes", 60, 60);
			    */
			    // Draw a line from (10,10) to (150,150)
			  
			  b1 = new JButton("Disable middle button");
			    b1.setVerticalTextPosition(AbstractButton.CENTER);
			    b1.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
			    b1.setMnemonic(KeyEvent.VK_D);
			    b1.setActionCommand("disable");

			    b2 = new JButton("Middle button");
			    b2.setVerticalTextPosition(AbstractButton.BOTTOM);
			    b2.setHorizontalTextPosition(AbstractButton.CENTER);
			    b2.setMnemonic(KeyEvent.VK_M);

			    b3 = new JButton("Enable middle button");
			    //Use the default text position of CENTER, TRAILING (RIGHT).
			    b3.setMnemonic(KeyEvent.VK_E);
			    b3.setActionCommand("enable");
			    b3.setEnabled(false);

			    //Listen for actions on buttons 1 and 3.
			    b1.addActionListener((ActionListener) this);
			    b3.addActionListener((ActionListener) this);

			    b1.setToolTipText("Click this button to disable "
			                      + "the middle button.");
			    b2.setToolTipText("This middle button does nothing "
			                      + "when you click it.");
			    b3.setToolTipText("Click this button to enable the "
			                      + "middle button.");
			   
			}

			public void actionPerformed(ActionEvent e) {
			    if ("disable".equals(e.getActionCommand())) {
			        b2.setEnabled(false);
			        b1.setEnabled(false);
			        b3.setEnabled(true);
			    } else {
			        b2.setEnabled(true);
			        b1.setEnabled(true);
			        b3.setEnabled(false);
			    }
			} 

		
			  public static void main(String arg[]){
				    // create an identifier named 'window' and
				    // apply it to a new BasicFrame object
				    // created using our constructor, above.f
				  
				   configFrame();
				  }
			 
				
}
