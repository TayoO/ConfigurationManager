package serverStuff;



import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComboBoxes;
import java.util.LinkedList;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class DropDownList extends JApplet {
private String[] description ;

private JTextField t = new JTextField(15);

private JComboBox c = new JComboBox();

private JButton b = new JButton("Add items");

private LinkedList<Integer> results;

private int count = 0;
public DropDownList(String [] options)
{
	description=options;
}
public void init() {
 for (int i = 0; i < 4; i++)
   c.addItem(description[count++]);
 t.setEditable(false);
 b.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
     if (count < description.length)
       c.addItem(description[count++]);
   }
 });
 c.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
	   results.push(c.getSelectedIndex());
     t.setText("index: " + c.getSelectedIndex() + "   "
         + ((JComboBox) e.getSource()).getSelectedItem());
   }
 });
 Container cp = getContentPane();
 cp.setLayout(new FlowLayout());
 cp.add(t);
 cp.add(c);
 cp.add(b);
}

public static void main(String[] args) {
	String [] example={"Ebullient", "Obtuse", "Recalcitrant",
			   "Brilliant", "Somnescent", "Timorous", "Florid", "Putrescent"};
	DropDownList x=new DropDownList(example);
 x.run( 200, 125);
}

public  void run( int width, int height) {
 JFrame frame = new JFrame();
 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 frame.getContentPane().add(this);
 frame.setSize(width, height);
 init();
 start();
 frame.setVisible(true);
}
} ///:~

